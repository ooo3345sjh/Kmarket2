package kr.co.kmarket.controller;
/**
 * 날짜 : 2023/02/11
 * 이름 : 서정현
 * 내용 : User 컨트롤러
 */

import javassist.NotFoundException;
import kr.co.kmarket.service.UserService;
import kr.co.kmarket.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
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

    @GetMapping("/register/{userType}")
    public String register(@PathVariable(value = "userType") String type) throws Exception {

        if("general".equals(type))
            return "user/registerGeneral";

        else if ("seller".equals(type))
            return "user/registerSeller";

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The path is invalid", new NotFoundException(""));
    }


    @PostMapping("/register/{userType}")
    public String register(@PathVariable(value = "userType") String type, UserVO user) throws Exception {
        log.info("UserController POST register...");

        log.debug(user.toString());
        log.debug(type);

        if(type != null && !type.isBlank()){
            if("general".equals(type))
                userService.saveGeneral(user);

            else if ("seller".equals(type))
                userService.saveSeller(user);

            return "redirect:/login";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The path is invalid", new NotFoundException(""));
    }

    @ResponseBody
    @GetMapping("/register/{uid}/check")
    public int checkUid(@PathVariable String uid){
        return userService.getDuplicateUserCount(uid);
    }


}
