package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {

    private ProductService service;

    @GetMapping(value = {"/", "/index"})
    public String index(Model m) throws Exception {

        Map<String, List<ProductVO>> map = service.selectCate();
        m.addAttribute("map", map);

        return "index";
    }

    @ResponseBody
    @GetMapping("auth")
    public String auth(){
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

}
