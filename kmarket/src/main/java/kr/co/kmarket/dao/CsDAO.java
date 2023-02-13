package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
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
      public CsVO selectarticle(Integer no);
      public List<CsVO> select5();
      public List<CsVO> selectarticles(SearchCondition sc);
      public int countAll(SearchCondition sc);
}
