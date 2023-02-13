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
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserService {

    private final TermsRepo termsRepo;
    private final UserDAO userDAO;
    private final PasswordEncoder encoder;

    private final EmailService emailService;


    /**
     * @return 약관 데이터
     */
    public TermsEntity getTerms(){
        return termsRepo.findById(1).orElse(null);
    }

    /**
     * @param uid 조회할 아이디
     * @return 조회된 행 갯수 반환
     */
    public int getDuplicateUserCount(String uid){
        return userDAO.countByUid(uid);
    }

    /**
     * @param email 조회할 이메일
     * @return 조회된 행 갯수 반환
     */
    public int getDuplicateEmailCount(String email){
        return userDAO.countByEmail(email);
    }

    /**
     * @param table 조회할 테이블
     * @param hp 조회할 번호
     * @return 조회된 행 갯수 반환
     */
    public int getDuplicateHpCount(String table, String hp){
        return userDAO.countByHp(table, hp);
    }

    /**
     * @param map 클라이언트에 전송할 모델객체
     */
    public void sendEmail(Map map){
        int result = getDuplicateEmailCount((String)map.get("email"));
        map.put("result", result);

        if(result == 0) emailService.send(map);
    }

    /**
     * @param uid 회원 아이디
     * @param type 회원 타입(구매자:1, 판매자:2, 관리자:3)
     * @return 등록된 행 갯수
     */
    public int saveUser(@Param(value = "uid") String uid, @Param(value = "type") Integer type){
        return userDAO.insertUser(uid, type);
    }

    /**
     * @param user 회원등록할 객체
     * @return 등록된 행 갯수
     */
    public int saveGeneral(UserVO user){
        settingUser(user);
        return userDAO.insertGeneral(user) == 1? userDAO.insertUser(user.getUid(), user.getType()) : 0;
    }

    /**
     * @param user 회원등록할 객체
     * @return 등록된 행 갯수
     */
    public int saveSeller(UserVO user){
        settingUser(user);
        user.setType(2); // 판매자는 type : 2
        return userDAO.inserSeller(user) == 1? userDAO.insertUser(user.getUid(), user.getType()) : 0;
    }

    private void settingUser(UserVO user) {
        // 유저 regip 저장 및 패스워드 인코딩
        String regip = getWebAuthenticationDetails().getRemoteAddress();
        user.setRegip(regip);
        user.setPass(encoder.encode(user.getPassword()));
    }

    /**
     * @return 모든 일반, 관리자 회원 정보 객체 리스트
     */
    public List<UserVO> findAllGeneral(){
        return userDAO.selectAllGeneralUser();
    }

    /**
     * @return 모든 판매자 회원 정보 객체 리스트
     */
    public List<UserVO> findAllSeller(){
        return userDAO.selectAllSellerUser();
    }

    /**
     * @return 모든 일반, 관리자 회원 정보 삭제한 행의 갯수
     */
    public int deleteAllGeneralUser(){
        return userDAO.deleteAllGeneralUser();
    }

    /**
     * @return 모든 판매자 회원 정보 삭제한 행의 갯수
     */
    public int deleteAllSellerUser(){
        return userDAO.deleteAllSellerUser();
    }

    /**
     * @return 아아디에 해당하는 일반, 관리자 회원 객체
     */
    public UserVO findGeneralUser(String uid){
        return userDAO.selectGeneralUser(uid);
    };

    /**
     * @return 아아디에 해당하는 판매자 회원 객체
     */
    public UserVO findSellerUser(String uid){
        return userDAO.selectSellerUser(uid);
    };

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private WebAuthenticationDetails getWebAuthenticationDetails(){
        return (WebAuthenticationDetails)getAuthentication().getDetails();
    }
}
