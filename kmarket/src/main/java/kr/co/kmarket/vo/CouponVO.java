package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 * 날짜 : 2023/02/24
 * 이름 : 서정현
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponVO {
    private int cp_id;
    private String uid;
    private int serialNo;
    private String cp_subject;
    private int cp_rate;
    private int cp_price;
    private int type;
    private int cp_minimum;
    private int cp_maximum;
    private String cp_start;
    private Date cp_end;
    private String couponDate;
}
