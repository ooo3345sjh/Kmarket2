package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.repository.CsRepo;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
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
public class CsService {

      @Autowired
      private CsDAO dao;

      @Autowired
      private CsRepo repo;

      public List<CsVO> selectnoti5(){
            return dao.selectnoti5();
      }
      public List<CsVO> selectqna5(){
            return dao.selectqna5();
      }
//    public void insertCs(CsVO vo){ dao.insertCs(vo); }
//
      public CsVO selectarticle (String csNo) {
            return dao.selectarticle(csNo);
      }
//
//    public List<CsVO> selectCsAll(int start) {
//        return dao.selectCsall(start);
//    }
      public void selectnotice(Model m, SearchCondition sc){
            int totalCnt = dao.countAll(sc);
            // 전체 페이지수
            int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize());
            // 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
            if(sc.getPage() > totalPage) sc.setPage(totalPage);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);  // 페이징 처리
            List<CsVO> articles =  dao.selectnotice(sc); 			  // 게시물 조회
            m.addAttribute("ph", pageHandler);
            m.addAttribute("articles", articles);
      }

}
