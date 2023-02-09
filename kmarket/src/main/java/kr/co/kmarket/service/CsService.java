package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.repository.CsRepo;
import kr.co.kmarket.vo.CsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//    public void selectCs () {}
//
//    public List<CsVO> selectCsAll(int start) {
//        return dao.selectCsall(start);
//    }

}
