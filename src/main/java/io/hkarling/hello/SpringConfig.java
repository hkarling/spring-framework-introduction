package io.hkarling.hello;

import io.hkarling.hello.repository.MemberRepository;
import io.hkarling.hello.repository.MemoryMemberRepository;
import io.hkarling.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /**
     * 자바 코드로 직접 스프링 빈 등록하기
     * XMl 설정은 잘 사용하지 않는다.
     * DI : 필드 주입, setter 주입, 생성자 주입(권장)
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
