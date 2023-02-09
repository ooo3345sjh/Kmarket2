package kr.co.kmarket.repository;
/**
 * @since 2023/02/08 라성준
 * @param
 * @return
 */

import kr.co.kmarket.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
