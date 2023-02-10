package kr.co.kmarket.dao;

import kr.co.kmarket.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {


    public int selectCount(String uid);
    public List<UserVO> selectAll();
    public List<UserVO> selectAllSellerUser();
    public List<UserVO> selectAllGeneralUser();
    public int insertGeneral(UserVO user);
    public int inserSeller(UserVO user);
    public void deleteAllSellerUser();
    public int deleteAllGeneralUser();
    public void update();
}
