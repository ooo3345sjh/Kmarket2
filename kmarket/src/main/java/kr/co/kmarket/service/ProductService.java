package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.repository.ProductRepo;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * product service
 * @since 2023/02/08
 * @author 라성준
 */
@Service
public class ProductService {

   @Autowired
   private ProductDAO dao;

   @Autowired
   private ProductRepo repo;

    /**
     * product insert service
     * @since 2023/02/08
     * @author 라성준
     */
    public void insertProduct(ProductVO vo){
        dao.insertProduct(vo);
    }

    /**
     * product select service
     * @since 2023/02/08
     * @author 라성준
     */
    public void selectProduct () {}

    /**
     * product list service
     * @since 2023/02/09
     * @author 라성준
     */
    public List<ProductVO> selectProducts(int cate1, int cate2, String sort, int start) {
        return dao.selectProducts(cate1, cate2, sort, start);
    }

    //public int selectCountTotalProduct() {
    //  int total = dao.selectCountTotalProduct();
    //   return total;
    //  }



    /**
     * product update service
     * @since 2023/02/08
     * @author 라성준
     */
    public int updateProduct(ProductVO vo) {
        return dao.updateProduct(vo);
    }

}
