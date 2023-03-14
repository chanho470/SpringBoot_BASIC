package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,  MemberRepository{
    // interface가 interface를 받을 때는 extends 사용
    // interfaces는 다중 상속 가능

    // 공통 적인 기능 들은 스프링 데이터에서 제공함
    
    // 복잡한 동적 쿼리는 Querydsl이라는 리이브러리 사용 가능
    @Override
    Optional<Member> findByName(String name); // 메세드 이름만으로 조회 기능 제공
}
