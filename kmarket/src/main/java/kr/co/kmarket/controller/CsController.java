package kr.co.kmarket.controller;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.Attribute;
import javax.sound.midi.Soundbank;
import java.awt.print.Printable;
import java.util.List;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */
@Slf4j
@Controller
public class CsController {

    public PageHandler ph;

    @Autowired
    private CsService service;

    @GetMapping("cs/cs_index")
    public String index(Model m,SearchCondition sc){

        List<CsVO> noti = service.selectnoti5();
        List<CsVO> qna5 = service.selectqna5();
        m.addAttribute("noti", noti);
        m.addAttribute("qna5", qna5);


        return "cs/cs_index";
    }
    @GetMapping("cs/notice/list")
    public String notice_list(Model m, SearchCondition sc){
        service.selectnotice(m,sc);
//        sc.setCate1("notice");
        log.info(sc.toString());
        m.addAttribute("sc",sc);


        return "cs/notice/list";
    }
    @GetMapping("cs/notice/view")
    public String notice_view(Model m,String csNo,String cate1, String cate2){
        CsVO article = service.selectarticle(csNo);
        m.addAttribute("csNo",csNo);
        m.addAttribute("article",article);
        m.addAttribute("cate2",cate2);
        m.addAttribute("cate1",cate1);
        return "cs/notice/view";
    }
    @GetMapping("cs/faq/list")
    public String faq_list(Model m,String cate1){
        m.addAttribute("type",cate1);
        return "cs/faq/list";
    }
    @GetMapping("cs/faq/view")
    public String faq_view(){
        return "cs/faq/view";
    }

    @GetMapping("cs/qna/list")
    public String qna_list(Model m,String cate1){
        m.addAttribute("type",cate1);
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
