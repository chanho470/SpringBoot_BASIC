package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberRepositoryTest;
import hello.hellospring.repository.MemoryMemeberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemeberRepository memberRepository ;

    @BeforeEach //dependency injection
    public void beforeEach(){
        memberRepository = new MemoryMemeberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join_회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");
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
        /* 예외
        * */
        /*try {
            memberService.join(member2);
            fail("예외가 발생해야합나디");
        }catch (IllegalStateException e){ // e.getMessage()와 "이미 존재하는 회원입니다"를 비교
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123");
        }*/
        //then

    }

    @Test
    void findMembers_회원조회() {
    }

    @Test
    void findOne() {
    }
}