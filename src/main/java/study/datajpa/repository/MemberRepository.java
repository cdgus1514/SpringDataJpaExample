package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();


    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(
            @Param("username") String username,
            @Param("age") int age
    );


    @Query("select new study.datajpa.dto.MemberDto( m.id, m.username, t.name) " +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();


    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);



    //Page<Member> findByAge(int age, Pageable pageable);
    //Slice<Member> findByAge(int age, Pageable pageable);

    // 여러 테이블 조인해서 결과 반환 시 별도로 카운트쿼리를 생성필요
    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

}
