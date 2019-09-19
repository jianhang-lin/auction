package work.jianhang.server.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import work.jianhang.server.entity.Comment;
import work.jianhang.server.entity.Product;
import work.jianhang.server.service.AuctionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuctionServiceImpl implements AuctionService {

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

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Comment> getAllComments() {
        return comments;
    }

}
