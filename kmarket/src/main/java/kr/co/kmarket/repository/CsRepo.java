package kr.co.kmarket.repository;

import kr.co.kmarket.entity.CsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * 날짜 : 2023/02/09
 * 이름 : 김동민
 * 내용 : cs controller
 * */
public interface CsRepo extends JpaRepository<CsEntity, Integer> {
}
