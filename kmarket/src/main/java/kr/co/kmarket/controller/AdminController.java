package kr.co.kmarket.controller;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String productList(Model m, SearchCondition sc, @AuthenticationPrincipal UserVO user) {
        log.info("adminController product list...");

        service.selectProductAdmin(m, sc, user);

        return "admin/product/list";
    }

    // 상품 삭제
    @ResponseBody
    @GetMapping("/product/deleteProduct")
    public Map deleteProduct(@RequestParam int prodNo) {
        log.info("deleteProduct...");
        // System.out.println("prodNo = " + prodNo);
        int result = service.deleteProduct(prodNo);
        System.out.println("result = " + result);

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 체크된 모든 상품 삭제
    @PostMapping("/product/deleteSelectedProduct")
    public String deleteSelectedProduct(@PathVariable("valueArr") String[] ajaxMsg) {
        log.info("deleteSelectedProduct...");

        int size = ajaxMsg.length;
        int result = 0;
        for (int i = 0; i < size; i++) {
//            result = service.deleteSelectedProduct(ajaxMsg[i]);
        }
        return null;
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
