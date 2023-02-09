package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductDAO dao;

    @Test
    void test_1(){
        Integer count = dao.select();
        System.out.println("count = " + count);
    }

}