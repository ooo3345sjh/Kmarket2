package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/product/register")
    public String register() {
        return "admin/product/register";
    }

    @GetMapping("/product/list")
    public String productList() {
        return "admin/product/list";
    }

    @GetMapping("/cs/list")
    public String csList() {
        return "admin/cs/list";
    }


}
