package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 메인 컨트롤러
 * @since 2023/02/13
 * @author 라성준
 */
@Controller
@AllArgsConstructor
public class MainController {

    @Autowired
    private ProductService service;

    @GetMapping(value = {"/", "/index"})
    public String index(Model m) {
//        List<Product_cate1VO> cate1s = service.selectCate1s;
        return "index";
    }

    @ResponseBody
    @GetMapping("auth")
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
