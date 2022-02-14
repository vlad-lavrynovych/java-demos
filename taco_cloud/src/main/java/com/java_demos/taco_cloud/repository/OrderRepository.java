package com.java_demos.taco_cloud.repository;

import com.java_demos.taco_cloud.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order save(Order order);

}
