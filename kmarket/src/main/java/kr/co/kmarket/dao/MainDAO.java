package kr.co.kmarket.dao;

import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate1VO;
import kr.co.kmarket.vo.Product_cate2VO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 메인 DAO
 * @since 2023/02/13
 * @author 라성준
 */
@Mapper
@Repository
public interface MainDAO {

    public List<Product_cate1VO> selectCate1s();
    public List<Product_cate2VO> selectCate2s();
    public List<ProductVO> selectProductBest();
}
