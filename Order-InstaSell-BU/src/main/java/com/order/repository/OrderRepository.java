package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.model.OrderDetail;
import com.order.model.User;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer>{

	public List<OrderDetail> findByUserName(String userName);

}
