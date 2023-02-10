package kr.co.kmarket.service;

import kr.co.kmarket.dao.UserDAO;
import kr.co.kmarket.vo.UserVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("1. 일반 회원등록")
    void test_1(){
        UserVO user = UserVO.builder()
                        .uid("user")
                        .name("홍길동")
                        .pass(passwordEncoder.encode("1234"))
                        .type(1)
                        .hp("010-1234-1234")
                        .email("1234@naver.com")
                        .gender(1)
                        .regip("0.0.0.127")
                        .build();

        int result = service.saveGeneral(user);
        assertThat(result).isEqualTo(1);

        UserVO admin = UserVO.builder()
                .uid("admin")
                .name("세종대왕")
                .pass(passwordEncoder.encode("1234"))
                .type(3)
                .hp("010-8282-5353")
                .email("5555@naver.com")
                .gender(1)
                .regip("0:0:0:0:0:0:0:1")
                .build();

        result = service.saveGeneral(user);
        assertThat(result).isEqualTo(1);


    }

    @Test
    @DisplayName("2. 판매 회원등록")
    void test_2(){
        UserVO user = UserVO.builder()
                .uid("seller")
                .pass(passwordEncoder.encode("1234"))
                .type(2)
                .zip("46729")
                .addr1("부산 강서구 가달1로 38")
                .addr2("3층")
                .company("(주)케이마켓")
                .ceo("강감찬")
                .bizRegNum("1234-1234")
                .comRegNum("1234-1234")
                .tel("051-123-4567")
                .manager("이순신")
                .managerHp("010-3030-2020")
                .fax("051-1234-5656")
                .regip("0:0:0:0:0:0:0:1")
                .build();

        int result = service.saveSeller(user);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("3. 모든 회원 조회")
    void test_3(){
        service.findAllGeneral();

        System.out.println("service = " + service);
    }

}