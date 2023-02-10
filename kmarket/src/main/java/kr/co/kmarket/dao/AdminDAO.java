package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 날짜 : 2023/02/09
 * 이름 : 김진우
 * 내용 : 관리자 DAO
 * */

@Mapper
@Repository
public interface AdminDAO {
    public void insertProductAdmin();
    public List<ProductVO> selectProductAdmin(SearchCondition sc);
    public int countProductAdmin(SearchCondition sc);
}
