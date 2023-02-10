package kr.co.kmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "km_cs")
public class CsEntity {

    @Id
    private int csNo;
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
