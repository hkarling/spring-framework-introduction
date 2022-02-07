package io.hkarling.hello.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import io.hkarling.hello.domain.Member;
import io.hkarling.hello.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService; //= new MemberService();
    MemoryMemberRepository memberRepository; //= new MemoryMemberRepository();

    @BeforeEach // 각 테스트 실행 전에 호출. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고 의존관계도 새로 맺어준다.
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회복_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        /*try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(ex.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}