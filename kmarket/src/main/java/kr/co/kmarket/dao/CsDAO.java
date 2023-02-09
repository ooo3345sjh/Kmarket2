package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsDAO {
//    public void insertCs(CsVO vo);
//    public int updateCs(CsVO vo);
//    public int deleteCs(int no);
//    public List<CsVO> selectCsAll(int start);

      public List<CsVO> selectnoti5();
      public List<CsVO> selectqna5();
}
