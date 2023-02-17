package kr.co.kmarket.service;

import kr.co.kmarket.dao.MainDAO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate1VO;
import kr.co.kmarket.vo.Product_cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 메인 서비스
 * @since 2023/02/13
 * @author 라성준
 */
@Service
public class MainService {

    @Autowired
    private MainDAO dao;

    public List<Product_cate1VO> selectCate1s() {
        return dao.selectCate1s();
    }

    public List<Product_cate2VO> selectCate2s() {
        return dao.selectCate2s();
    }

    /**
     * 2023/02/13 /라성준 /베스트 상품 불러오기
     * @return
     */
    public List<ProductVO> selectProductBest() {
        List<ProductVO> bests = dao.selectProductBest();

        for (ProductVO best : bests) {
            best.setDisPrice((int) (best.getPrice() * (1 - (best.getDiscount() / 100.0))));
        }

        // 이미지 파일 경로 설정
        bests = bests.stream().map(p -> {
            int cate1 = p.getCate1();
            int cate2 = p.getCate2();
            String thumb1 = cate1 + "/" + cate2 + "/" + p.getThumb1(); // /cate1/cate2/thumb1
            p.setThumb1(thumb1);
            return p;

        }).collect(Collectors.toList());

        return bests;
    }

    /**
     * @since 2023/02/15 /라성준
     * 카테고리 1,2 값 불러오기
     * @param cate1
     * @return
     */
    public List<Product_cate2VO> selectCate2WithCate1(String cate1) {
        return dao.selectCate2WithCate1(Integer.parseInt(cate1));
    }

    /**
     * @since 2023/02/15 /라성준
     * 메인 상품 8개 불러오기
     * @param mode
     * @return
     */
    public List<ProductVO> selectProductMode(String mode) {
        List<ProductVO> products = dao.selectProductMode(mode);

        for(ProductVO product : products) {
            product.setDisPrice((int) (product.getPrice() * (1 - ( product.getDiscount() / 100.0 ))));
        }

        // 이미지 파일 경로 설정
        products = products.stream().map(p -> {
            int cate1 = p.getCate1();
            int cate2 = p.getCate2();

            String thumb2 = cate1 + "/" + cate2 + "/" + p.getThumb2(); // /cate1/cate2/thumb1
            p.setThumb2(thumb2);

            return p;
        }).collect(Collectors.toList());
         //System.out.println("list : " + products);
        return products;
    }

}
