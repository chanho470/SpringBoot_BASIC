package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리 하는 entity
public class Member
{
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 iD 생성 옵숀
    private Long id;
    //@Column(name = "username")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
// jpa는 객체와 orm 기술