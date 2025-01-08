package com.backendTask.task3.repository;

import com.backendTask.task3.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long>  {
    // 쿼리 메서드
    List<Orders> findByMember_MemberId(Long memberId);
}
