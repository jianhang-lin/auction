package work.jianhang.server.controller;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import work.jianhang.server.entity.Comment;
import work.jianhang.server.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class AuctionController {

    private static List<Product> products = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();
    static {
        products.add(new Product(1, "第一个商品", 1.99f, 3.5f, "这是第一个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(2, "第二个商品", 1.99f, 1.5f, "这是第二个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(3, "第三个商品", 2.99f, 3.5f, "这是第三个商品", Lists.newArrayList("硬件设备")));
        products.add(new Product(4, "第四个商品", 6.99f, 2.5f, "这是第四个商品", Lists.newArrayList("软件设备")));
        products.add(new Product(5, "第五个商品", 9.99f, 3.5f, "这是第五个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(6, "第六个商品", 11.99f, 4.5f, "这是第六个商品", Lists.newArrayList("图书")));

        comments.add(new Comment(1, 1, "2017-02-02 22:22:22", "张三", 3, "东西不错"));
        comments.add(new Comment(2, 1, "2017-03-03 22:22:22", "李四", 4, "东西还不错"));
        comments.add(new Comment(3, 1, "2017-04-04 22:22:22", "王五", 2, "东西不错"));
        comments.add(new Comment(4, 2, "2017-05-05 22:22:22", "赵六", 4, "东西不错"));
    }

    @GetMapping("/")
    public String home() {
        return "Hello Auction";
    }

    @GetMapping("/api/products")
    public List<Product> getProducts(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String price,
                                     @RequestParam(required = false) String category) {
        List<Product> result = products;
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
        return products.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @GetMapping("/api/product/{id}/comments")
    public List<Comment> getCommentsByProductId(@PathVariable int id) {
        return comments.stream().filter(c -> c.getProductId() == id).collect(Collectors.toList());
    }
}
