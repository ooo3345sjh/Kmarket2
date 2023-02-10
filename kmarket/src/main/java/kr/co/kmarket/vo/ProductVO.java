package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * productVo
 * @since 2023/02/08
 * @author 라성준
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

    private int prodNo;
    private Integer cate1;
    private int cate2;
    private String prodName;
    private String descript;
    private String company;
    private String seller;
    private int price;
    private int discount;
    private int point;
    private int stock;
    private int sold;
    private int delivery;
    private int hit;
    private int score;
    private int review;
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;
    private String duty;
    private String receipt;
    private String bizType;
    private String origin;
    private String ip;
    private String rdate;
}
