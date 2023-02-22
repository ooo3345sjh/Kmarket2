package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.service.UserService;
import kr.co.kmarket.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 2023/02/22
 * @author 서정현
 * @apiNote 마이페이지 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/my")
@AllArgsConstructor
public class myController {

    private UserService userService;
    private MyService myService;

    /**
     * @apiNote 마이페이지 > 홈
     */
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

    /**
     * @apiNote 마이페이지 > 쿠폰
     */
    @GetMapping("/coupon")
    public String coupon(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get coupon start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "coupon");
        return "my/coupon";
    }

    /**
     * @apiNote 마이페이지 > 나의 정보
     */
    @GetMapping("/info")
    public String info(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get info start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "info");
        return "my/info";
    }

    /**
     * @apiNote 마이페이지 > 전체주문내역
     */
    @GetMapping("/ordered")
    public String ordered(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get info start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "ordered");
        return "my/ordered";
    }

    /**
     * @apiNote 마이페이지 > 포인트내역
     */
    @GetMapping("/point")
    public String point(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get info start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "point");
        return "my/point";
    }

    /**
     * @apiNote 마이페이지 > 문의하기
     */
    @GetMapping("/qna")
    public String qna(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get info start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "qna");
        return "my/qna";
    }

    /**
     * @apiNote 마이페이지 > 나의 리뷰
     */
    @GetMapping("/review")
    public String review(Model m, @AuthenticationPrincipal UserVO user){
        log.info("myController Get info start...");

        // 마이페이지 요약 정보 데이터 조회
        user = myService.getNavInfo(user.getUid());

        m.addAttribute("user", user);
        m.addAttribute("type", "review");
        return "my/review";
    }

    /**
     * @apiNote 마이페이지 > 홈 > 최근주문내역에서 상호명 클릭시 출력되는 판매자 정보 데이터 조회
     */
    @ResponseBody
    @GetMapping("/home/sellerInfo/{uid}")
    public Map sellerInfo(@PathVariable String uid){
        log.info("myController Get sellerInfo start...");
        log.info(uid);
        UserVO user = userService.findSellerUser(uid);
        user.setAddr("["+user.getZip()+"] "+ user.getAddr1() + " " + user.getAddr2());

        log.info(user.toString());
        Map map = new HashMap<>();
        map.put("sellerInfo", user);
        return map;
    }

    /**
     * @apiNote 마이페이지 > 홈 > 최근주문내역 > 판매자 정보 > 문의하기에서 등록하기 버튼 클릭
     */
    @ResponseBody
    @GetMapping("/home/sendEmail")
    public Map qnaSendToSeller(@RequestBody Map map){
        log.info("myController Post qnaSendToSeller start...");

        log.info(map.toString());

        return map;
    }

}
