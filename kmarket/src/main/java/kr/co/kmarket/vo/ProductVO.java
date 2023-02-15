package kr.co.kmarket.vo;

import lombok.*;
import java.util.List;

/**
 * productVo
 * @since 2023/02/08
 * @author 라성준
 */
@Getter
@Setter
@ToString
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

    // 추가 필드
    private List<ProductVO> cateList;
    private String type;
    private int disPrice; // 할인율 적용 값

}
