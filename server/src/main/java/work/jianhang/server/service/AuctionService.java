package work.jianhang.server.service;

import work.jianhang.server.entity.Comment;
import work.jianhang.server.entity.Product;

import java.util.List;

public interface AuctionService {

    List<Product> getAllProducts();

    List<Comment> getAllComments();

}
