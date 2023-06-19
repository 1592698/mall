package com.example.mall.service;

import com.example.mall.domain.members.Member;
import com.example.mall.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //final이나 @NonNull이 붙은 필드에 생성자를 생성해줌,
//빈에 생성자가 1개이고 생성자의 파라미터 타입이 빈으로 등록이 가능하다면 @Autowired 어노테이션이 없이 의존성 주입이 가능함
@Transactional //로직을 처리하다가 에러가 발생하면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줌
@Service
public class MemberService {
        private final MemberRepository memberRepository;

        public Member saveMember(Member member){
            validateDuplicateMember(member);

            return memberRepository.save(member);
        }

        private void validateDuplicateMember(Member member){
            Member findMember = memberRepository.findByEmail(member.getEmail());
            if(findMember !=null){
                throw new IllegalStateException("이미 가입된 회원입니다.");
            }
        }
    }

