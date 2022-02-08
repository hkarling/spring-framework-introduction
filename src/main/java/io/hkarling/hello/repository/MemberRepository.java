package io.hkarling.hello.repository;

import io.hkarling.hello.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
