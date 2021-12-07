package study.datajpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;


    // JPA proxy에서 사용해야 하기 떄문에 기본생성자 필요
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
