package kr.co.kmarket.vo;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
