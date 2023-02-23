package kr.co.kmarket.controller;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.BannerVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
* 날짜 : 2023/02/09
* 이름 : 김진우
* 내용 : 관리자 controller
* */

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    // 관리자 메인페이지
    @GetMapping("")
    public String index(Model m) {
        service.selectLatest(m);
        return "admin/index";
    }

    // 상품현황
    @GetMapping("/product/list")
    public String productList(Model m, SearchCondition sc, @AuthenticationPrincipal UserVO user) {
        log.info("productList...");
        service.selectProductAdmin(m, sc, user);
        return "admin/product/list";
    }

    // 상품 등록 화면
    @GetMapping("/product/register")
    public String register() {
        return "admin/product/register";
    }

    // 상품 등록
    @PostMapping("/product/register")
    public String register(ProductVO product, @AuthenticationPrincipal UserVO user) {
        log.info("register product...");
        service.insertProductAdmin(product, user);
        return "admin/product/register";
    }

    // 상품 수정
    @ResponseBody
    @PostMapping("/product/modifyProduct")
    public Map modifyProduct(@RequestBody ProductVO product) {
        log.info("Modifying product...");
        int result = service.modifyProduct(product);

        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    // 상품삭제
    @ResponseBody
    @GetMapping("/product/deleteProduct")
    public Map deleteProduct(@RequestParam String prodNo) {
        log.info("deleteProduct...");
        int result = service.deleteProduct(prodNo);

        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    // 상품 삭제(체크 박스)
    @ResponseBody
    @PostMapping("/product/deleteSelectedProduct")
    public Map deleteSelectedProduct(@RequestParam("valueArr") String[] ajaxMsg) {
        log.info("deleteSelectedProduct...");

        int size = ajaxMsg.length;
        int result = 0;
        for (int i=0; i<size; i++) {
            result = service.deleteProduct(ajaxMsg[i]);
        }

        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    /////////////////////////////// 고객센터 ///////////////////////////////

    // 고객센터 리스트
    @GetMapping("/cs/list")
    public String csList(Model m, SearchCondition sc) {
        log.info("csList...");
        service.selectCsAdmins(m, sc);
        m.addAttribute("cate1", sc.getCate1());
        return "admin/cs/list";
    }

    // 고객센터 글보기
    @GetMapping("/cs/view")
    public String view(Model m, int csNo, String cate1) {
        log.info("viewController...");
        CsVO article = service.selectCsAdmin(csNo);
        m.addAttribute("article", article);
        m.addAttribute("cate1", cate1);
        return "/admin/cs/view";
    }

    // 고객센터 글쓰기 화면
    @GetMapping("cs/write")
    public String write(Model m, CsVO vo) {
        log.info("writeController...");
        m.addAttribute("cate1", vo.getCate1());
        return "/admin/cs/write";
    }

    // 고객센터 글쓰기
    @PostMapping("cs/write")
    public String write(CsVO vo, @AuthenticationPrincipal UserVO user) {
        log.info("writeController...");
        service.insertCs(vo, user);
        return "redirect:/admin/cs/list?cate1=" + vo.getCate1();
    }

    // 고객센터 글 수정 화면
    @GetMapping("cs/modify")
    public String modify(Model m, String cate1, int csNo) {
        log.info("modifyGetController...");
        CsVO article = service.selectCsAdmin(csNo);
        m.addAttribute("article", article);
        m.addAttribute("cate1", cate1);
        return "/admin/cs/modify";
    }

    // 고객센터 글 수정
    @PostMapping("cs/modify")
    public String modify(CsVO vo) {
        log.info("modifyPostController...");
        service.updateCs(vo);
        return "redirect:/admin/cs/view?csNo=" + vo.getCsNo() + "&cate1=" + vo.getCate1();
    }

    // qna 답변달기
    @GetMapping("cs/reply")
    public String reply(CsVO vo) {
        log.info("reqlyController...");
        service.updateComment(vo);
        return "redirect:/admin/cs/view?cate1="+ vo.getCate1() + "&csNo=" + vo.getCsNo();
    }

    // 고객센터 게시글 삭제
    @ResponseBody
    @GetMapping("/cs/delete")
    public Map deleteCs(@RequestParam String csNo) {
        log.info("deleteCs...");
        int result = service.deleteCs(csNo);

        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    // 고객센터 체크된 게시글 삭제
    @ResponseBody
    @PostMapping("/cs/deleteChecked")
    public Map deleteCheckedCs(@RequestParam("valueArr") String[] checkedCs) {
        log.info("deleteCheckedCs...");
        int size = checkedCs.length;
        int result = 0;
        for (int i=0; i<size; i++) {
            result = service.deleteCs(checkedCs[i]);
        }
        Map map = new HashMap();
        map.put("result", result);
        return map;
    }

    // 배너관리 화면
    @GetMapping("/config/banner")
    public String banner(Model m) {
        service.selectBanner(m);
        return "admin/config/banner";
    }

    @PostMapping("/config/banner")
    public String banner(BannerVO vo) {
        log.info("bannerController...");
        System.out.println("vo = " + vo);
        service.insertBanner(vo);
        return "admin/config/banner";
    }
}
