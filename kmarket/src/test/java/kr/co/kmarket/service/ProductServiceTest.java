package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.util.Mapper;

import java.io.File;
import java.security.Provider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductDAO dao;

    @Value("${spring.servlet.multipart.location}")
    String uploadPath;

    @Test
    void test_1(){
        // 프로젝트 디렉토리에 대해 상대 경로
        File configFile = new File(uploadPath + "/file");
        System.out.println(configFile.isDirectory());
    }

    @Test
    void test_2() throws Exception {
        Map<String, List<ProductVO>> map = service.selectCate();
        System.out.println(map);


    }

}