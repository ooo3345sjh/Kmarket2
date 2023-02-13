package kr.co.kmarket.controller;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
* 날짜 : 2023/02/09
* 이름 : 김진우
* 내용 : 관리자 controller
* */

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    // 관리자 메인페이지
    @GetMapping("")
    public String index() {
        return "admin/index";
    }

    // 상품현황
    @GetMapping("/product/list")
    public String productList(Model m, SearchCondition sc) { // @AuthenticationPrincipal User user

        log.info("adminController product list...");
        service.selectProductAdmin(m, sc);

        return "admin/product/list";
    }

    // 상품등록
    @GetMapping("/product/register")
    public String register() {
        return "admin/product/register";
    }

    // 관리자 고객센터
    @GetMapping("/cs/list")
    public String csList() {
        return "admin/cs/list";
    }


}
