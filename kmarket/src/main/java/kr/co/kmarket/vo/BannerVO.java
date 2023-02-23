package kr.co.kmarket.vo;

/*
 * 날짜 : 2023/02/23
 * 이름 : 김진우
 * 내용 : bannerVO
 * */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerVO {

    private int bannerNo;
    private String bannerName;
    private int width;
    private int height;
    private String color;
    private String link;
    private String position;
    private String dateBegin;
    private String dateEnd;
    private String timeBegin;
    private String timeEnd;
    private int hit;
    private String image;
    private int manage;

    // 추가 필드
    private MultipartFile file;
}
