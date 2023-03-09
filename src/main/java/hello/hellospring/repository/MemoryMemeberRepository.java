package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemeberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence 키값 생성 0,1,2....
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //사람이 추가될 때 마다 1 씩증가
        store.put(member.getId(),member); // 아이디랑 맴버를 저장 (0,chkim) 이런식으로 저장되겠지
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id)); // 조회한 값이 Null일 수 있기 때문에 Optional.ofNullable로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //stream을 이용해 map 안의 값을 조회
                .filter(member -> member.getName().equals(name)) // 이 조건으로 필터링 한다.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}


