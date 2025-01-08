package com.backendTask.task2.repository;

import com.backendTask.task2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 쿼리 메서드
    Optional<Member> findByEmail(String email);

}
