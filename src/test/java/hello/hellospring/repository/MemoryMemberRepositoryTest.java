package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemeberRepository repository =new MemoryMemeberRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }// 테스트 셋들은 독립적으로 생성해야 하기 때문에 공용 데이터를 비워둬야한다.

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result  = repository.findByID(member.getId()).get(); // 반환 타입이 optional 로 get으로 값을 꺼냄
        System.out.println("result  = " + (result == member)); // result = true

        // 콘솔 창을 이용해 결과 값이 참임을 확인할 수 있지만, Assertions으로 케이스의 값을 검증할 수 있다.
        //Assertions.assertEquals(member,null); 오류 뜸
        Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6으로 설정 변수 모든이름 변경 가능함.
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6으로 설정 변수 모든이름 변경 가능함.
        member2.setName("Spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
