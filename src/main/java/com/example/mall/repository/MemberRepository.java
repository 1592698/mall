package com.example.mall.repository;

import com.example.mall.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    //회원가입시 중복된 회원이 있는지 검사하기 위해 이메일로 회원을 검사하는 메소드
}
