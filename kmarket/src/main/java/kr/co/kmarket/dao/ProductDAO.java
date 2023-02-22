package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate2VO;
import kr.co.kmarket.vo.ReviewVO;
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
    List<ProductVO> selectCate() throws Exception;
    public int updateProduct(ProductVO vo);

    /**
     * product list DAO
     * 2023/02/21 /라성준
     * @param cate1
     * @param cate2
     * @param sort
     * @param start
     * @return
     */
    public List<ProductVO> selectProducts(
                  @Param("cate1") int cate1,
                  @Param("cate2") int cate2,
                  @Param("sort") String sort,
                  @Param("start") int start,
                  @Param("search") String search);

    /**
     * product catename 가져오기
     * 2023/02/21 /라성준
     * @param cate1
     * @param cate2
     * @return
     */
    public Product_cate2VO getCateName(
                  @Param("cate1") int cate1,
                  @Param("cate2") int cate2);

    /**
     * product 전체 갯수
     * 2023/02/21 /라성준
     * @param cate1
     * @param cate2
     * @return
     */
    public int getCountTotal(@Param("cate1") int cate1,
                             @Param("cate2") int cate2,
                             @Param("search") String search);

    /**
     * product review 가져오기
     * 2023/02/22 /라성준
     * @param prodNo
     * @param start
     * @return
     */
    public List<ReviewVO> selectReviews(@Param("prodNo") int prodNo,
                                        @Param("start") int start);


}
