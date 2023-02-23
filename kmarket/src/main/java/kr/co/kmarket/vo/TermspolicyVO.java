package kr.co.kmarket.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TermspolicyVO {
    private int no;
    private String title;
    private String content;
    private String cate;
    private int cateno;
}
