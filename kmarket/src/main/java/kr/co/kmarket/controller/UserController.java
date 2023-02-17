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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
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
    @GetMapping("/register/{uid}/checkId")
    public Map checkUid(@PathVariable String uid){
        log.info("UserController GET checkUid...");

        Map map = new HashMap();
        map.put("result", userService.getDuplicateUserCount(uid));

        return map;
    }

    @ResponseBody
    @PostMapping("/register/sendEmail")
    public Map sendEmail(@RequestBody Map map){
        log.info("UserController POST sendEmail...");

        // 메일 전송
        userService.sendEmail(map);

        return map;
    }

    @ResponseBody
    @PostMapping("/findId")
    public Map findId(@RequestBody Map map){
        log.info("UserController POST findId...");
        log.info(map.toString());
        if(map.get("name") != null){
            // 메일 전송
            userService.findId_EmailAuth(map);
        } else if(map.get("uid") != null){

        }

        return map;
    }

    @GetMapping("/findId/result")
    public String findId(Map map, Model m){
        String name = (String)map.get("name");
        String email = (String)map.get("email");
        UserVO user = userService.findByNameAndEmail(email, name);
        log.info(user.toString());
        m.addAttribute("user", user);
        return "user/findIdResult";
    }

    @ResponseBody
    @GetMapping("/register/checkHp/{type}")
    public Map checkHp(@RequestParam String hp, @PathVariable String type){
        log.info("UserController GET checkHp...");

        int result = 0;

        // 메일 전송
        try {
            result = userService.getDuplicateHpCount(type, hp);
        } catch (Exception e){
            log.error(e.getMessage());
            result = -1;
        }

        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    @GetMapping("/findId")
    public String findId(){
        return "user/findId";
    }


    @GetMapping("/findPw")
    public String findPw(){
        return "user/findPw";
    }

    @GetMapping("/findPw/result")
    public String findPwResult(){
        return "user/findPwResult";
    }
}
