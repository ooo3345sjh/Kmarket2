package kr.co.kmarket.controller;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate1VO;
import kr.co.kmarket.vo.Product_cate2VO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MainService service;

    @GetMapping(value = {"/", "/index"})
    public String index(Model m) {
        List<Product_cate1VO> cate1s = service.selectCate1s();
        List<Product_cate2VO> cate2s = service.selectCate2s();
        List<ProductVO> bests = service.selectProductBest();
        m.addAttribute("cate1s", cate1s);
        m.addAttribute("cate2s", cate2s);
        m.addAttribute("bests", bests);
        return "index";
    }

    @ResponseBody
    @GetMapping("auth")
    public String auth(){
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

}
