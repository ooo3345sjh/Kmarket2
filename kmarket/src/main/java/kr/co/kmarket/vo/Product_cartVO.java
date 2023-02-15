package kr.co.kmarket.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product_cartVO {

    private int cartNo;
    private int prodNo;
    private String uid;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
    private String rdate;
}
