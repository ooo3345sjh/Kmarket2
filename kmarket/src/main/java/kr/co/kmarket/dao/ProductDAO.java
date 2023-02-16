package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * productDAO
 * @since 2023/02/08 라성준
 */
@Mapper
@Repository
public interface ProductDAO {
    public void insertProduct(ProductVO vo);
    public List<ProductVO> selectProducts();
    List<ProductVO> selectCate() throws Exception;
    List<ProductVO> selectCate2() throws Exception;

    public int updateProduct(ProductVO vo);

}
