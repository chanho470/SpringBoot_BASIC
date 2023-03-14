package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemeberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/*
* 테스트는 반복 가능해야 한다.
* */
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional //테스트 케이스에 Transactional 어노테이션이 있으면 트렌젝션을 먼저 실행하고 테스트가 종료 되면 롤백해 DB에 넣었던 데이터가 반영이 안된다
                // 다음 테스트 반복 가능하다. 테스트 메서드 마다 실행함
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository ;


    @Test
    //@Commit @Transactional 안타고 디비에 반영됨
    void join_회원가입() {
        //given
        Member member = new Member();
        member.setName("test 초w");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); //저장한 아이디가 잘있는지 확인
    }

    @Test
    public void 중복회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("중복회원");

        Member member2 = new Member();
        member2.setName("중복회원");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,() ->memberService.join(member2));// memberService.join(member2)이 로직 실행하는데 IllegalStateException.class이 예외가 발생해야한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}