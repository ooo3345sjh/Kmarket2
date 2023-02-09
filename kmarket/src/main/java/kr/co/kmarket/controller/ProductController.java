package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("product/list")
    public String productList() {
        return "product/list";
    }

    @GetMapping("product/register")
    public String productRegister() {
        return "product/register";
    }

}
