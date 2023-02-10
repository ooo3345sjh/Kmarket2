package kr.co.kmarket.controller;

import kr.co.kmarket.service.UserService;
import kr.co.kmarket.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @GetMapping("/signup/{type}")
    public String signup(Model m, @PathVariable String type){

        m.addAttribute("terms", userService.getTerms());
        m.addAttribute("type", type);

        return "user/signup";
    }

    @GetMapping("/register/{type}")
    public String register(@PathVariable String type){

        if("general".equals(type))
            return "user/registerGeneral";

        else if ("seller".equals(type))
            return "user/registerSeller";

        return null;
    }

    @ResponseBody
    @GetMapping("/register/{uid}/check")
    public int checkUid(@PathVariable String uid){
        return userService.getDuplicateUserCount(uid);
    }

    @PostMapping("/register/{type}")
    public String register(@PathVariable String type, UserVO user){

        if("general".equals(type))
            userService.saveGeneral(user);

        else if ("seller".equals(type))
            userService.saveGeneral(user);

        return "redirect:/login";
    }
}
