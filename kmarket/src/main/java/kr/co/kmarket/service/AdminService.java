package kr.co.kmarket.service;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    /////////////////////////// 상품 등록 ///////////////////////////
    @Transactional
    public int insertProductAdmin(ProductVO product) {

        // 파일 업로드
        fileupload(product);
        // 상품 등록
        int result = dao.insertProductAdmin(product);

        return result;
    }

    /////////////////////////// 상품 조회 ///////////////////////////
    public void selectProductAdmin(Model m, SearchCondition sc, UserVO user) {

        int totalCnt = dao.countProductAdmin(sc); // 전체 상품 갯수

        int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize()); // 전체 페이지수
        if(sc.getPage() > totalPage) sc.setPage(totalPage); // 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
        PageHandler pageHandeler = new PageHandler(totalCnt, sc); // 페이징 처리

        // SearchCondition에 uid, type 담기
        sc.setType(user.getType());
        sc.setUid(user.getUid());

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

    /////////////////////////// 상품 정보 수정 ///////////////////////////
    public int modifyProduct(ProductVO product) {
        log.info("modifyService...");
        return dao.modifyProduct(product);
    }


    /////////////////////////// 상품 삭제 ///////////////////////////
    public int deleteProduct(String prodNo) {
        return dao.deleteProduct(prodNo);
    }

    // 프로퍼티 벨류 값을 uploadPath에 대입
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // 파일 업로드 메서드
    public void fileupload(ProductVO product) {

        MultipartFile[] files = product.getFile();
        List<String> names = new ArrayList<>();

        // 파일 저장 경로 설정
        String path = new File(uploadPath + "/" + product.getCate1() + "/" + product.getCate2()).getAbsolutePath();

        for (MultipartFile file : files) {
            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString()+ext;
            names.add(nName);

            // 파일 저장
            try {
                file.transferTo(new File(path, nName));
            } catch (IllegalStateException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
        product.setThumb1(names.get(0));
        product.setThumb2(names.get(1));
        product.setThumb3(names.get(2));
        product.setDetail(names.get(3));
    }

    /////////////////////////// 관리자 고객센터 게시판 불러오기 ///////////////////////////
    public List<CsVO> selectCsAdmin(Model m, SearchCondition sc) {

        int totalCnt = dao.countCsAdmin(sc); // 전체 게시물 갯수
        int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize()); // 전체 페이지 수
        if(sc.getPage() > totalPage) sc.setPage(totalPage);
        PageHandler pageHandeler = new PageHandler(totalCnt, sc); // 페이징 처리

        List<CsVO> articles = dao.selectCsAdmin(sc);

        m.addAttribute("articles", articles);
        m.addAttribute("ph", pageHandeler);

        return articles;
    }
}
