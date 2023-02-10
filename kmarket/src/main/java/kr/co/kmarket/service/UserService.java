package kr.co.kmarket.service;

/**
 *  날짜 :2023/02/10
 *  이름 : 서정현
 *  내용 : 회원 서비스
 */

import kr.co.kmarket.dao.UserDAO;
import kr.co.kmarket.entity.TermsEntity;
import kr.co.kmarket.repository.TermsRepo;
import kr.co.kmarket.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final TermsRepo termsRepo;
    private final UserDAO userDAO;

    /**
     *
     * @return 약관 데이터
     */
    public TermsEntity getTerms(){
        return termsRepo.findById(1).orElse(null);
    }

    /**
     * 
     * @param uid 조회할 아이디
     * @return 조회된 행 갯수 반환
     */
    public int getDuplicateUserCount(String uid){
        return userDAO.selectCount(uid);
    }

    /**
     * @param user 회원등록할 객체
     * @return 등록된 행 갯수
     */
    public int saveGeneral(UserVO user){
        return userDAO.insertGeneral(user);
    }

    /**
     * @param user 회원등록할 객체
     * @return 등록된 행 갯수
     */
    public int saveSeller(UserVO user){
        return userDAO.inserSeller(user);
    }

    /**
     * @return 모든 일반, 관리자 회원 정보 객체 리스트
     */
    public List<UserVO> findAllGeneral(){
        return userDAO.selectAllGeneralUser();
    }

    /**
     *
     * @return 모든 판매자 회원 정보 객체 리스트
     */
    public List<UserVO> findAllSeller(){
        return userDAO.selectAllSellerUser();
    }
}
