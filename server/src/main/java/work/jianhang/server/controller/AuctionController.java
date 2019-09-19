package work.jianhang.server.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;
import work.jianhang.server.entity.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuctionController {

    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "第一个商品", 1.99f, 3.5f, "这是第一个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(2, "第二个商品", 1.99f, 1.5f, "这是第二个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(3, "第三个商品", 2.99f, 3.5f, "这是第三个商品", Lists.newArrayList("硬件设备")));
        products.add(new Product(4, "第四个商品", 6.99f, 2.5f, "这是第四个商品", Lists.newArrayList("软件设备")));
        products.add(new Product(5, "第五个商品", 9.99f, 3.5f, "这是第五个商品", Lists.newArrayList("电子产品","硬件设备")));
        products.add(new Product(6, "第六个商品", 11.99f, 4.5f, "这是第六个商品", Lists.newArrayList("图书")));
    }

    @GetMapping("/")
    public String home() {
        return "Hello Auction";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().get();
    }
}
