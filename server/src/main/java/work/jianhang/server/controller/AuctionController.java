package work.jianhang.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import work.jianhang.server.entity.Comment;
import work.jianhang.server.entity.Product;
import work.jianhang.server.service.AuctionService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/")
    public String home() {
        return "Hello Auction";
    }

    @GetMapping("/api/products")
    public List<Product> getProducts(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String price,
                                     @RequestParam(required = false) String category) {
        List<Product> result = auctionService.getAllProducts();
        if (!StringUtils.isEmpty(title) && !Objects.equals("null", title)) {
            result = result.stream().filter(p -> p.getTitle().indexOf(title) != -1).collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(price) && !Objects.equals("null", price) && result.size() > 0) {
            result = result.stream().filter(p -> p.getPrice() <= Float.parseFloat(price)).collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(category) && !Objects.equals("-1", category) && !Objects.equals("null", category) && result.size() > 0) {
            result = result.stream().filter(p -> p.getCategories().contains(category)).collect(Collectors.toList());
        }
        return result;
    }

    @GetMapping("/api/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return auctionService.getAllProducts().stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @GetMapping("/api/product/{id}/comments")
    public List<Comment> getCommentsByProductId(@PathVariable int id) {
        return auctionService.getAllComments().stream().filter(c -> c.getProductId() == id).collect(Collectors.toList());
    }
}
