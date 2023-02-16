package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
/**
 * product 컨트롤러
 * @since 2023/02/10
 * @author 라성준
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("product/list")
    public String list() {


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

    @GetMapping("product/view")
    public String view() {
        return "product/view";
    }



}
