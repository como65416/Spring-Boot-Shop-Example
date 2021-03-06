package com.comoco.demoshop.repository;

import com.comoco.demoshop.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {

}
