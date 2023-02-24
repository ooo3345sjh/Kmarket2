//package kr.co.kmarket.service;
//
//import kr.co.kmarket.dao.MyDAO;
//import kr.co.kmarket.vo.UserVO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class MyServiceTest {
//
//    @Autowired
//    MyDAO myDAO;
//
//    @Test
//    @DisplayName("1. 주문, 주문아이템 DB 추가")
//    void test_1(){
//
//        for(int i = 1; i<=200; i++){
//            long ordNo = 2302220045332937L + i;
//            myDAO.test1(String.valueOf(ordNo));
//            myDAO.test2(String.valueOf(ordNo));
//        }
//    }
//
//    @Test
//    @DisplayName("2. 이번달 값 구하기")
//    void test_2(){
//        for(int i=5;i>0;i--){
//            System.out.println("LocalDate.now().minusMonths(i).getMonthValue() = " + LocalDate.now().minusMonths(i).getMonthValue());
//        }
//    }
//
//}