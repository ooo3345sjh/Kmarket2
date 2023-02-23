package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.repository.CsRepo;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.TermspolicyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */
@Service
@Slf4j
public class CsService {

      @Autowired
      private CsDAO dao;

      @Autowired
      private CsRepo repo;

      public List<CsVO> select5(){
            return dao.select5();
      }

      public CsVO selectarticle (Integer no) {
            return dao.selectarticle(no);
      }

      public List<CsVO> selectfaqtypes(SearchCondition sc){
            return dao.selectfaqtypes(sc);
      }

      public void insertqna(CsVO qna){
            dao.insertqna(qna);
      }
      public void selectarticles(Model m, SearchCondition sc){
            int totalCnt = dao.countAll(sc);
            // 전체 페이지수
            int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize());
            // 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
            if(sc.getPage() > totalPage) sc.setPage(totalPage);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);  // 페이징 처리
            List<CsVO> articles =  dao.selectarticles(sc); 			  // 게시물 조회
            m.addAttribute("ph", pageHandler);
            m.addAttribute("articles", articles);
      }
      public List<TermspolicyVO> selecttermspolicy (TermspolicyVO terms){
            return dao.selecttermspolicy(terms);
      }
}
