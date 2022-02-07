package io.hkarling.hello.repository;

import static org.assertj.core.api.Assertions.*;

import io.hkarling.hello.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        Member member2 = new Member();
        member2.setName("spring2");

        // when
        repository.save(member1);
        repository.save(member2);

        // then
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    void findAll() {
        // given
        Member member1 =new Member();
        member1.setName("spring1");
        Member member2 =new Member();
        member2.setName("spring2");

        // when
        repository.save(member1);
        repository.save(member2);

        // then
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}