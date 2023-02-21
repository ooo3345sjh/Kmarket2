package kr.co.kmarket.dao;

import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
 * 날짜 : 2023/02/09
 * 이름 : 김진우
 * 내용 : 관리자 DAO
 * */

@Mapper
@Repository
public interface AdminDAO {
    public int insertProductAdmin(ProductVO product);
    public List<ProductVO> selectProductAdmin(SearchCondition sc);
    public int countProductAdmin(SearchCondition sc);
    public int deleteProduct(String prodNo);
    public int modifyProduct(ProductVO product);
    public List<CsVO> selectCsAdmins(SearchCondition sc);
    public CsVO selectCsAdmin(int csNo);
    public int countCsAdmin(SearchCondition sc);
    public int deleteCs(String csNo);
}
