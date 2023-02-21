package kr.co.kmarket.controller;

import kr.co.kmarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
public class myController {

    @GetMapping("/home")
    public String home(Model m){
        m.addAttribute("type", "home");
        return "my/home";
    }
    @ResponseBody
    @PostMapping("/home")
    public String home(@ModelAttribute UserVO vo){
        log.info(vo.toString());
        return "my/home";
    }

    @GetMapping("/coupon")
    public String coupon(Model m){
        m.addAttribute("type", "coupon");
        return "my/coupon";
    }

    @GetMapping("/info")
    public String info(Model m){
        m.addAttribute("type", "info");
        return "my/info";
    }

    @GetMapping("/ordered")
    public String ordered(Model m){
        m.addAttribute("type", "ordered");
        return "my/ordered";
    }

    @GetMapping("/point")
    public String point(Model m){
        m.addAttribute("type", "point");
        return "my/point";
    }

    @GetMapping("/qna")
    public String qna(Model m){
        m.addAttribute("type", "qna");
        return "my/qna";
    }

    @GetMapping("/review")
    public String review(Model m){
        m.addAttribute("type", "review");
        return "my/review";
    }

}
