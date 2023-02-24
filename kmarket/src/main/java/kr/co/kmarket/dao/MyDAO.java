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
    int countOrderLog(SearchCondition sc);
    List<PointVO> selectPointLogs(SearchCondition sc);
    int countPointLog(SearchCondition sc);
    List<PointVO> selectPointLog(@Param(value = "uid") String uid);
    List<CsVO> selectCsLog(@Param(value = "uid") String uid);
    List<ReviewVO> selectReviewLog(@Param(value = "uid") String uid);
    int insertReview(ReviewVO reviewVO);
    List<CouponVO> selectCoupon(@Param(value = "uid") String uid);
    List<ReviewVO> selectReview(SearchCondition sc);
    int countReview(SearchCondition sc);
    List<CsVO> selectQna(SearchCondition sc);
    int countQna(SearchCondition sc);
    OrderVO selectOrderAndOrderItem(@Param(value = "ordNo") String ordNo, @Param(value = "prodNo") String prodNo);
    int updateOrdState(@Param(value = "ordNo") String ordNo);
    UserVO selectInfo_g(@Param(value = "uid") String uid);
    UserVO selectInfo_s(@Param(value = "uid") String uid);
    int test1(@Param(value = "ordNo") String ordNo);
    int test2(@Param(value = "ordNo") String ordNo);
}
