package com.xenby.demo.repository;

import com.xenby.demo.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {

}
