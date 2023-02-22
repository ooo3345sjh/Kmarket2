package kr.co.kmarket.service;


import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MyService {

    @Autowired
    private MyDAO myDAO;

    /**
     * @apiNote 마이페이지 요약 정보 안내의 데이터 조회
     * @param uid 회원아이디
     * @return 회원 정보 객체
     */
    public UserVO getNavInfo(String uid){
        return myDAO.selectNavInfo(uid);
    }

    /**
     * @apiNote 마이페이지 홈 데이터(최근주문내역, 포인트적립내역, 상품평, 문의내역) 조회
     * @param uid
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public Map getMyHomeInfo(String uid){

        Map map = new HashMap<>();
        myDAO.selectOrderLog(uid).stream().forEach(e -> log.info(e.toString()));
        map.put("orderLog", myDAO.selectOrderLog(uid));
        map.put("pointLog", myDAO.selectPointLog(uid));
        map.put("reviewLog", myDAO.selectReviewLog(uid));
        map.put("csLog", myDAO.selectCsLog(uid));

        return map;
    }

}
