package kr.co.kmarket.service;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 날짜 : 2023/02/09
 * 이름 : 김진우
 * 내용 : 관리자 service
 * */

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminDAO dao;

    public void insertProductAdmin() {

    }

    // 상품 조회
    public void selectProductAdmin(Model m, SearchCondition sc) {

        int totalCnt = dao.countProductAdmin(sc); // 전체 상품 갯수

        /** 검색 페이지 > 전체 페이수일 경우 실행 **/
        int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize()); // 전체 페이지수

        // 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
        if(sc.getPage() > totalPage) sc.setPage(totalPage);

        PageHandler pageHandeler = new PageHandler(totalCnt, sc); // 페이징 처리

        List<ProductVO> list = dao.selectProductAdmin(sc); // 상품 조회

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
        // System.out.println("list : " + list);

        m.addAttribute("ph", pageHandeler);
        m.addAttribute("products", list);

    }
}
