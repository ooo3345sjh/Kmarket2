package kr.co.kmarket.repository;

import kr.co.kmarket.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepo extends JpaRepository<TermsEntity, Integer> {
}
