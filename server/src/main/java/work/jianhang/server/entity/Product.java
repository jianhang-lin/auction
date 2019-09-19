package work.jianhang.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private int id;

    private String title;

    private float price;

    private float rating;

    private String desc;

    private List<String> categories;
}
