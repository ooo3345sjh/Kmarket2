package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate2VO;
import kr.co.kmarket.vo.ReviewVO;
import kr.co.kmarket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * product list 컨트롤러
 * @since 2023/02/10
 * @author 라성준
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("product/list")
    public String list(Model model,
                       @RequestParam(value="cate1",
                               required = false,
                               defaultValue = "0") int cate1,
                       @RequestParam(value="cate2",
                               required = false,
                               defaultValue = "0") int cate2,
                       @RequestParam(value="search",
                               required = false) String search,
                               String sort, String pg)
    {
                        Product_cate2VO cateName = null;

        // 게시글 출력 갯수
        int count = 10;
        // 현재 페이지 번호
        int currentPage = service.getCurrentPage(pg);
        // 페이지 시작값
        int start = service.getLimitStart(currentPage, count);
        // 전체 게시물 갯수
        int total = service.getCountTotal(cate1, cate2, search);
        // 페이지 마지막 번호
        int lastPageNum = service.getLastPageNum(total, count);
        // 페이지 그룹 start, end 번호
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum);

        List<ProductVO> products = service.selectProducts(cate1, cate2, sort, start, search);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        return "product/list";
    }

    @GetMapping("product/cart")
    public String cart() {
        return "product/cart";
    }

    @GetMapping("product/complete")
    public String complete() {
        return "product/complete";
    }

    @GetMapping("product/order")
    public String order() {
        return "product/order";
    }

    /**
     * product view
     * 2023/02/22 /라성준
     * @param cate1
     * @param cate2
     * @param prodNo
     * @param model
     * @param pg
     * @return
     */
    @GetMapping("product/view")
    public String view(int cate1, int cate2, int prodNo, Model model, String pg){

        // 카테고리 이름 가져오기
        Product_cate2VO cateName = service.getCateName(cate1, cate2);

        // 상품 가져오기
        ProductVO product = service.selectProduct(prodNo);

        // 출력갯수
        int count = 5;
        // 현재 페이지
        int currentPage = service.getCurrentPage(pg);
        // 페이지 시작값
        int start = service.getLimitStart(currentPage, count);
        // 전체 게시물 갯수
        int total = service.getCountTotalForReview(prodNo);
        // 페이지 마지막 번호
        int lastPageNum = service.getLastPageNum(total, count);
        // 페이지 그룹 start, end
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum);

        // 리뷰 가져오기
        List<ReviewVO> reviews = service.selectReviews(prodNo, start);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        return "product/view";
    }

}
