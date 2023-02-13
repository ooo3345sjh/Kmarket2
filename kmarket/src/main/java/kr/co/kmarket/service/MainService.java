package kr.co.kmarket.service;

import kr.co.kmarket.dao.MainDAO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate1VO;
import kr.co.kmarket.vo.Product_cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ProductVO> selectProductBest() {
        List<ProductVO> bests = dao.selectProductBest();
        for (ProductVO best : bests) {

        }
        return bests;
    }

}
