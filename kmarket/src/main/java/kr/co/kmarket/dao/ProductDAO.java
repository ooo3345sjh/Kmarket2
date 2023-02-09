package kr.co.kmarket.dao;

import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @since 2023/02/08 라성준
 */
@Mapper
@Repository
public interface ProductDAO {
    public void insertProduct(ProductVO vo);
    public List<ProductVO> selectProducts(int start);
    //public int selectCountTotalProduct() {}
    public int updateProduct(ProductVO vo);
    public int deleteProduct(int no);
}
