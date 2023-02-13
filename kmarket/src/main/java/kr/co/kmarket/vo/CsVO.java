package kr.co.kmarket.vo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsVO {

    private Integer csNo;
    private String uid;
    private String cate1;
    private String cate2;
    private String type;
    private String title;
    private String content;
    private String rdate;
    private int hit;
    private String comment;
    private String regip;
}
