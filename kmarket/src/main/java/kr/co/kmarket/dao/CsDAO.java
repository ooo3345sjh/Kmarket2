package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.TermspolicyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */
@Mapper
@Repository
public interface CsDAO {
      public CsVO selectarticle(Integer no);
      public List<CsVO> select5();
      public List<CsVO> selectarticles(SearchCondition sc);
      public int countAll(SearchCondition sc);
      public List<CsVO> selectfaqtypes(SearchCondition sc);
      public void insertqna(CsVO qna);
      public List<TermspolicyVO> selecttermspolicy(TermspolicyVO terms);
}
