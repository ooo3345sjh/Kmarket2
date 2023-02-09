package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.repository.ProductRepo;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @since 2023/02/08 라성준
 * @param
 * @return
 */
@Service
public class ProductService {

   @Autowired
   private ProductDAO dao;

    @Autowired
    private ProductRepo repo;

    public void insertProduct(ProductVO vo){
        dao.insertProduct(vo);
    }

    public void selectProduct () {}

    public List<ProductVO> selectProducts(int start) {
        return dao.selectProducts(start);
    }

    //public int selectCountTotalProduct() {
    //  int total = dao.selectCountTotalProduct();
    //   return total;
    //  }

    public int deleteProduct (int prodNo) {
        return dao.deleteProduct(prodNo);
    }

    public int updateProduct(ProductVO vo) {
        return dao.updateProduct(vo);
    }



}
