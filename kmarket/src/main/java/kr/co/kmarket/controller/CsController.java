package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.Attribute;

@Controller
public class CsController {

    @GetMapping("cs/cs_index")
    public String index(){
        return "cs/cs_index";
    }
    @GetMapping("cs/notice/list")
    public String notice_list(){
        return "cs/notice/list";
    }
    @GetMapping("cs/notice/view")
    public String notice_view(){
        return "cs/notice/view";
    }
    @GetMapping("cs/faq/list")
    public String faq_list(){
        return "cs/faq/list";
    }
    @GetMapping("cs/faq/view")
    public String faq_view(){
        return "cs/faq/view";
    }

    @GetMapping("cs/qna/list")
    public String qna_list(){
        return "cs/qna/list";
    }
    @GetMapping("cs/qna/view")
    public String qna_view(){
        return "cs/qna/view";
    }
    @GetMapping("cs/qna/write")
    public String qna_write(){
        return "cs/qna/write";
    }
}
