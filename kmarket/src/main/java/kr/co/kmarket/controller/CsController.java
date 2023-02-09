package kr.co.kmarket.controller;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.Attribute;
import javax.sound.midi.Soundbank;
import java.awt.print.Printable;
import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService service;

    @GetMapping("cs/cs_index")
    public String index(Model m){

        List<CsVO> noti = service.selectnoti5();
        List<CsVO> qna5 = service.selectqna5();

        m.addAttribute("noti", noti);
        m.addAttribute("qna5", qna5);


        return "cs/cs_index";
    }
    @GetMapping("cs/notice/list")
    public String notice_list(Model m,String cate1){
        m.addAttribute("type",cate1);
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
