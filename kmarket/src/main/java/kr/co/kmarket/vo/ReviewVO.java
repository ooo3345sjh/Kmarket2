package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {
    private int revNo;
    private int prodNo;
    private String uid;
    private String content;
    private int rating;
    private String regip;
    private String rdate;

    // 추가 필드
    private String prodName;
}
