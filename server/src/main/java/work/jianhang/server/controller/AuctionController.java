package work.jianhang.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuctionController {

    @GetMapping("/")
    public String home() {
        return "Hello Auction";
    }

    @GetMapping("/products")
    public String getProducts() {
        return "接收到商品查询请求";
    }
}
