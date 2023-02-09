package kr.co.kmarket.repository;

import kr.co.kmarket.entity.CsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsRepo extends JpaRepository<CsEntity, Integer> {
}
