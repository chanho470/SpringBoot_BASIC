package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemeberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional //데이터를 저장 변경할 때 필요하다.
public class MemberService {
    // ctrl + shift + t 단축키 test 생성 ===> test>java>hello.hellospring>service>MemberServiceTest
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    *  회원 가입
    * */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원x

            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {

        /*Optional<Member> result = memberRepository.findByName(member.getName()); //Optional 이 뭔지
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }//ctrl+alt+m 메서드로 만드는 단축키
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){

        return memberRepository.findAll();

    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
