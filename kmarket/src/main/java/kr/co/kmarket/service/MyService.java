package kr.co.kmarket.service;


import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.dao.UserDAO;
import kr.co.kmarket.utils.PageHandler;
import kr.co.kmarket.utils.SearchCondition;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.ReviewVO;
import kr.co.kmarket.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class MyService {

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
        myDAO.selectOrderLogLimit5(uid).stream().forEach(e -> log.info(e.toString()));
        map.put("orderLog", myDAO.selectOrderLogLimit5(uid));
        map.put("pointLog", myDAO.selectPointLog(uid));
        map.put("reviewLog", myDAO.selectReviewLog(uid));
        map.put("csLog", myDAO.selectCsLog(uid));

        return map;
    }

    public int writeReview(ReviewVO reviewVO){
        return myDAO.insertReview(reviewVO);
    }

    public OrderVO getDetailOrder(String ordNo, String prodNo){
        OrderVO orderVO = myDAO.selectOrderAndOrderItem(ordNo, prodNo);
        
        // 할인가격 계산
        OrderItemVO orderItemVO = orderVO.getOrderItemVO();
        int price = orderItemVO.getPrice();
        int count = orderItemVO.getCount();
        int distountPrice = (int)(price * (orderItemVO.getDiscount()/100.0))*count + orderItemVO.getPoint();
        orderItemVO.setDiscountPrice(distountPrice);
        
        return orderVO;
    }

    public int updateOrdState(String ordNo){
        return  myDAO.updateOrdState(ordNo);
    }

    public void getOrderLog(String uid, SearchCondition sc, Model m){
        int total = myDAO.countOrderLog(uid);
        PageHandler ph = new PageHandler(total, sc);
        List<OrderVO> list = myDAO.selectOrderLog(sc);

        log.info(ph.toString());
        log.info(list.toString());
        m.addAttribute("ph", ph);
        m.addAttribute("orderList", list);
    }

    public void getNavMonth(){
        LocalDate.now().getMonthValue();
    }






}
