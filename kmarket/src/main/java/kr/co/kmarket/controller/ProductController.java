package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("product/list")
    public String list(Model model, int cate1, int cate2, String sort) {
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
