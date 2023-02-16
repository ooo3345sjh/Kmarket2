package kr.co.kmarket.controller;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_cate1VO;
import kr.co.kmarket.vo.Product_cate2VO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


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
        List<ProductVO> hits = service.selectProductMode("hit");
        List<ProductVO> discounts = service.selectProductMode("discount");
        List<ProductVO> recommends = service.selectProductMode("score");
        List<ProductVO> news = service.selectProductMode("rdate");


        m.addAttribute("cate1s", cate1s);
        m.addAttribute("cate2s", cate2s);
        m.addAttribute("bests", bests);
        m.addAttribute("hits", hits);
        m.addAttribute("recommends", recommends);
        m.addAttribute("news" , news);
        m.addAttribute("discounts", discounts);

        return "index";
    }


    @ResponseBody
    @GetMapping("auth")
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
