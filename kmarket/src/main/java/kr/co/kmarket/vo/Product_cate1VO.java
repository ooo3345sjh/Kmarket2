package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * product_cate1Vo
 * @since 2023/02/13
 * @author 라성준
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product_cate1VO {
    private int cate1;
    private int cate2;
    private String c2Name;

}
