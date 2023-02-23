package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.repository.ProductRepo;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * product service
 * @since 2023/02/08
 * @author 라성준
 */
@Slf4j
@Service
public class ProductService {


    @Autowired
    private ProductDAO dao;


    /**
     * product insert service
     *
     * @author 라성준
     * @since 2023/02/08
     */
    public void insertProduct(ProductVO vo) {
        dao.insertProduct(vo);
    }

    /**
     * product select service
     *
     * @author 라성준
     * @since 2023/02/08
     */
    public ProductVO selectProductkdm(ProductVO vo) {
        // 조회수 + 1
        dao.updateProductHitkdm(vo);
        return dao.selectProductkdm(vo);
    }

    /**
     * product list service
     *
     * @author 라성준
     * @since 2023/02/09
     */
    public List<ProductVO> selectProducts(int cate1, int cate2, String sort, int start, String search) {
        return dao.selectProducts(cate1, cate2, sort, start, search);
    }

    /**
     * product update service
     *
     * @author 라성준
     * @since 2023/02/08
     */
    public int updateProduct(ProductVO vo) {
        return dao.updateProduct(vo);
    }
    public ProductVO getCateNamekdm(ProductVO vo){
        return dao.getCateNamekdm(vo);

    }
    public List<ReviewVO> selectReviewskdm(ProductVO vo){
        log.info("selectreview....");
        return dao.selectReviewskdm(vo);
    }

    /**
     * @throws Exception
     * @author 라성준
     * @since 2023/02/10
     */
    public Map<String, List<ProductVO>> selectCate() throws Exception {
        List<ProductVO> cateList = dao.selectCate();
        // 이미지 파일 경로 설정
        cateList = cateList.stream().map(p -> {
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
        System.out.println("catelist : " + cateList);

        return cateList.stream().collect(Collectors.groupingBy(ProductVO::getType));

    }

    /**
     * product catename 가져오기
     * 2023/02/21 /라성준
     *
     * @param cate1
     * @param cate2
     * @return
     */
    public Product_cate2VO getCateName(int cate1, int cate2) {
        return dao.getCateName(cate1, cate2);
    }

    /**
     * product 상품 갯수
     * 2023/02/21 /라성준
     *
     * @param cate1
     * @param cate2
     * @return
     */
    public int getCountTotal(int cate1, int cate2, String search) {
        return dao.getCountTotal(cate1, cate2, search);
    }

    /**
     * page start
     * 2023/02/21 /라성준
     *
     * @param currentPage
     * @param count
     * @return
     */
    public int getLimitStart(int currentPage, int count) {
        return (currentPage - 1) * count;
    }

    /**
     * page group
     * 2023/02/21 /라성준
     *
     * @param currentPage
     * @param lastPage
     * @return
     */
    public int[] getPageGroupNum(int currentPage, int lastPage) {

        int groupCurrent = (int) Math.ceil(currentPage / 10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if (groupEnd > lastPage) {
            groupEnd = lastPage;
        }

        int[] group = {groupStart, groupEnd};

        return group;
    }

    /**
     * current page
     * 2023/02/21 /라성준
     *
     * @param pg
     * @return
     */
    public int getCurrentPage(String pg) {
        int currentPage = 1;

        if (pg != null) {
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    /**
     * last page
     * 2023/02/21 /라성준
     *
     * @param total
     * @param count
     * @return
     */
    public int getLastPageNum(int total, int count) {

        int lastPage = 0;

        if (total % count == 0) {
            lastPage = (total / count);
        } else {
            lastPage = (total / count) + 1;
        }

        if (lastPage == 0) {
            lastPage = 1;
        }

        return lastPage;
    }

    /**
     * product review 가져오기
     * 2023/02/22 /라성준
     *
     * @param prodNo
     * @param start
     * @return
     */
    public List<ReviewVO> selectReviews(int prodNo, int start) {
        return dao.selectReviews(prodNo, start);
    }

    /**
     * product review 값 가져오기
     * 2023/02/22 /라성준
     * @param prodNo
     * @return
     */
    public int getCountTotalForReview(@Param("prodNo") int prodNo) {
        return dao.getCountTotalForReview(prodNo);


    }

    @Transactional
    public ProductVO selectProduct(int prodNo){

        ProductVO product = dao.selectProduct(prodNo);

        // 조회수 + 1
        dao.updateProductHit(prodNo);

        return product;
    }
}



