package kr.co.kmarket.dao;

import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDAO {
    public void insertProduct(ProductVO vo);
//    public int updateProduct(ProductVO vo);
//    public int deleteProduct(int no);
//    public List<ProductVO> selectProducts(int start);
}
