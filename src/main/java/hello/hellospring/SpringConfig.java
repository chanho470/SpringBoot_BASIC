package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

   /* private DataSource dataSource; // 디비 사용을 위함
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    /*private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    */

    private final MemberRepository memberRepository; // 데이터 jpa 사용을 위함
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

/*    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemeberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/
}
