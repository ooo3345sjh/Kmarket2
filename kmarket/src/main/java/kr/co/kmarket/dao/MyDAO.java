package kr.co.kmarket.dao;

/*
 * 날짜 : 2023/02/22
 * 이름 : 서정현
 * 내용 : 마이페이지 DAO
 */
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyDAO {
    UserVO selectNavInfo(@Param(value = "uid") String uid);
    List<OrderVO> selectOrderLogLimit5(@Param(value = "uid") String uid);
    List<OrderVO> selectOrderLog(SearchCondition sc);
    int countOrderLog(@Param(value = "uid") String uid);
    List<PointVO> selectPointLog(@Param(value = "uid") String uid);
    List<CsVO> selectCsLog(@Param(value = "uid") String uid);
    List<ReviewVO> selectReviewLog(@Param(value = "uid") String uid);
    int insertReview(ReviewVO reviewVO);
    OrderVO selectOrderAndOrderItem(@Param(value = "ordNo") String ordNo, @Param(value = "prodNo") String prodNo);
    int updateOrdState(@Param(value = "ordNo") String ordNo);
    int test1(@Param(value = "ordNo") String ordNo);
    int test2(@Param(value = "ordNo") String ordNo);
}
