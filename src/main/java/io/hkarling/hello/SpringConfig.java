package io.hkarling.hello;

import io.hkarling.hello.repository.MemberRepository;
import io.hkarling.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
////    @PersistenceContext
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 자바 코드로 직접 스프링 빈 등록하기
     * XMl 설정은 잘 사용하지 않는다.
     * DI : 필드 주입, setter 주입, 생성자 주입(권장)
     */

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

    /**
     * 객체 지향적인 설계가 좋은 이유.
     *   -> 다형성의 활용 : 인터페이스를 두고 구현체를 갈아끼워 기능을 변경시킴.
     *      스프링은 이걸 빈(bean)을 통해 DI(Dependency Injection)을 사용하여 기존 코드를
     *      전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
     *
     * 개방-폐쇄 원칙(OCP, Open-Closed Principle)
     *   - 확장에는 열려있고, 수정과 변경에는 닫혀있다.
     */
}
