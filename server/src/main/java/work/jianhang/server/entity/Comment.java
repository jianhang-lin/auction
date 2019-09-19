package work.jianhang.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private int id;
    private int productId;
    private String timestamp;
    private String user;
    private float rating;
    private String content;
}
