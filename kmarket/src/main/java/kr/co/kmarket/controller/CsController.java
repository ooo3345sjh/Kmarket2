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
    /*
     이름 : 김동민
     내용 : 고객센터 메인화면
     */

    @GetMapping("cs")
    public String index(Model m,SearchCondition sc){
        List<CsVO> cate5 = service.select5();
        m.addAttribute("sc",sc);
        m.addAttribute("cate5", cate5);
        return "cs/cs_index";
    }
    /*
     이름 : 김동민
     내용 : 고객센터 공지사항 목록
     */
    @GetMapping("cs/notice/list")
    public String notice_list(Model m,SearchCondition sc){
        service.selectarticles(m,sc);

        m.addAttribute("sc",sc);
        return "cs/notice/list";
    }
    /*
     이름 : 김동민
     내용 : 고객센터 공지사항 상세
     */
    @GetMapping("cs/notice/view")
    public String notice_view(Model m,Integer no,SearchCondition sc){
        sc.setNo(no);
        CsVO article = service.selectarticle(no);
        m.addAttribute("article",article);
        m.addAttribute("sc",sc);
        return "cs/notice/view";
    }
    @GetMapping("cs/faq/list")
    public String faq_list(Model m,SearchCondition sc){
        service.selectarticles(m,sc);
        m.addAttribute("sc",sc);
        return "cs/faq/list";
    }
    @GetMapping("cs/faq/view")
    public String faq_view(Model m,Integer no,SearchCondition sc){
        sc.setNo(no);
        CsVO article = service.selectarticle(no);
        m.addAttribute("article",article);
        m.addAttribute("sc",sc);
        return "cs/faq/view";
    }

    @GetMapping("cs/qna/list")
    public String qna_list(Model m,SearchCondition sc){
        service.selectarticles(m,sc);
        m.addAttribute("sc",sc);
        return "cs/qna/list";
    }
    @GetMapping("cs/qna/view")
    public String qna_view(Model m,Integer no,SearchCondition sc){
        sc.setNo(no);
        CsVO article = service.selectarticle(no);
        m.addAttribute("article",article);
        m.addAttribute("sc",sc);
        return "cs/qna/view";
    }
    @GetMapping("cs/qna/write")
    public String qna_write(){
        return "cs/qna/write";
    }
}
