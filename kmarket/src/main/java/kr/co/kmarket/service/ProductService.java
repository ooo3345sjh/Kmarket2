package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.repository.ProductRepo;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * @since 2023/02/10
     * @author 라성준
     * @throws Exception
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

        System.out.println("a" + cateList);
        return cateList.stream().collect(Collectors.groupingBy(ProductVO::getType));

    }

}
