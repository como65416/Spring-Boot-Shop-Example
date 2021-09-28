package com.xenby.demo.repository;

import com.xenby.demo.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>, JpaSpecificationExecutor<OrderProduct> {

}
