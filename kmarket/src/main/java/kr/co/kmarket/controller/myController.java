package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
@AllArgsConstructor
public class myController {

    private MyService myService;

    @GetMapping("/home")
    public String home(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get home start...");
        
        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());
        log.info(user.toString());
        
        // 마이페이지 홈 데이터 조회(최근주문내역, )
        Map map = myService.getMyHomeInfo(user.getUid());

        // 모델 저장
        m.addAttribute("user", user);
        m.addAttribute("map", map);
        m.addAttribute("type", "home");
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
