package io.hkarling.hello.service;

import io.hkarling.hello.domain.Member;
import io.hkarling.hello.repository.MemberRepository;
import io.hkarling.hello.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository) { // Dependency Injection : 외부에서 주입, 생성자 주입
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // ctrl + alt + shift + T : refactor code
        memberRepository.findByName(member.getName())
            .ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 아이디로 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
