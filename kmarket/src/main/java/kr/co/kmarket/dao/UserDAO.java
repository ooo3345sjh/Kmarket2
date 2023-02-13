package kr.co.kmarket.dao;

/*
 * 날짜 : 2023/02/09
 * 이름 : 서정현
 * 내용 : 회원 DAO
 */
import kr.co.kmarket.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {


    public int selectCount(String uid);
    public List<UserVO> selectAll();
    public List<UserVO> selectAllSellerUser();
    public List<UserVO> selectAllGeneralUser();
    public int insertUser(@Param(value = "uid") String uid, @Param(value = "type")Integer type);
    public int insertGeneral(UserVO user);
    public int inserSeller(UserVO user);
    public int deleteAllSellerUser();
    public int deleteAllGeneralUser();
    public UserVO selectGeneralUser(@Param(value = "uid") String uid);
    public UserVO selectSellerUser(@Param(value = "uid") String uid);
    public void update();
}
