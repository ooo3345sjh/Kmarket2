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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() throws IOException {

//        if(error != null && error.equals("w")){
//            rttr.addFlashAttribute("error", error);
//            return "redirect:/user/login/error";
//        }

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
    @PostMapping("/find/emailAuth")
    public Map findId_emailAuth(@RequestBody Map map){
        log.info("UserController POST findId_emailAuth...");
        log.info(map.toString());

        if(map.get("name") != null){
            // 메일 전송
            userService.findId_EmailAuth(map);
        } else if(map.get("uid") != null){
            // 메일 전송
            userService.findPw_EmailAuth(map);
        }

        return map;
    }

    @PostMapping("/findId/result")
    public String findIdResult(@RequestParam Map map, RedirectAttributes rttr, Model m){
        log.info("UserController POST findIdResult...");
        String name = (String)map.get("name");
        String email = (String)map.get("email");

        UserVO user = userService.findByNameAndEmail(name, email);
        if(user == null){
            rttr.addFlashAttribute("error", "error");
            return "redirect:/user/findId";
        }

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

    @GetMapping("/findPw/reulst")
    public String findPwResult(){
        return "user/findPwResult";
    }

    @PostMapping("/findPw/result")
    public String findPwResult(@RequestParam Map map, RedirectAttributes rttr, Model m){
        log.info("UserController POST findPwResult...");
        String uid = (String)map.get("uid");
        String email = (String)map.get("email");

        UserVO user = userService.findByUidAndEmail(uid, email);
        if(user == null){
            rttr.addFlashAttribute("error", "error");
            return "redirect:/user/findPw";
        }

        m.addAttribute("user", user);
        return "user/findPwResult";
    }

    @PostMapping("/findPw/reset")
    public String resetPw(
            @RequestParam(value = "pass1") String pass,
            @RequestParam(value = "uid") String uid,
            RedirectAttributes rttr
    )
    {
        log.info("UserController POST resetPw...");
        log.info(pass);
        log.info(uid);

        int result = userService.resetPw(uid, pass);

        if(result != 1){
            rttr.addFlashAttribute("error", "error");
            return "redirect:/user/findPw/result";
        }

        rttr.addFlashAttribute("success", "successPw");
        return "redirect:/user/login";
    }
}
