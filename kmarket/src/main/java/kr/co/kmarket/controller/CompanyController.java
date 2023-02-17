package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 회사소개 컨트롤러
 * 2023/02/17 라성준
 */
@Controller
public class CompanyController {

    @GetMapping("company/index")
    public String index() {
        return "company/index";
    }

    @GetMapping("company/introduce")
    public String introduce() {
        return "company/introduce";
    }

    @GetMapping("company/notice")
    public String notice() {
        return "company/notice";
    }

    @GetMapping("company/promote")
    public String promote() {
        return "company/promote";
    }

    @GetMapping("company/manage")
    public String manage() {
        return "company/manage";
    }

}
