package kr.co.kmarket.service;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 날짜 : 2023/02/09
 * 이름 : 김진우
 * 내용 : 관리자 service
 * */

@Service
public class AdminService {

    @Value("${spring.servlet.multipart.location}")
    String uploadPath;


    @Autowired
    private AdminDAO dao;

    public void insertProductAdmin() {

    }
    public List<ProductVO> selectProductAdmin() {
        List<ProductVO> list = dao.selectProductAdmin();

        // 이미지 파일 경로 설정
        list = list.stream().map(p -> {
           int cate1 = p.getCate1();
           int cate2 = p.getCate2();
           String thumb1 = cate1 + "/" + cate2 + "/" + p.getThumb1(); // /cate1/cate2/thumb1
           String thumb2 = cate1 + "/" + cate2 + "/" + p.getThumb2();
           String thumb3 = cate1 + "/" + cate2 + "/" + p.getThumb3();
           p.setThumb1(thumb1);
           p.setThumb2(thumb2);
           p.setThumb3(thumb3);
           return p;
        }).collect(Collectors.toList());

        return list;
    }
}
