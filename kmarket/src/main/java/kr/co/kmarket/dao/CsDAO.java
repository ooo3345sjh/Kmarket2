package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.SearchCondition;
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
//    public void insertCs(CsVO vo);
//    public int updateCs(CsVO vo);
//    public int deleteCs(int no);
//    public List<CsVO> selectCsAll(int start);
      public CsVO selectarticle(String csNo);
      public List<CsVO> selectnoti5();
      public List<CsVO> selectqna5();
      public List<CsVO> selectnotice(SearchCondition sc);
      public int countAll(SearchCondition sc);
}
