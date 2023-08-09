package jpabook.jpashop2.domain.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;
    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("박희재");

        //when
        Long saveId = memberService.join(member);
        //em.flush();
        //then
        assertThat(member).isEqualTo(memberRepository.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("조");
        Member member2 = new Member();
        member2.setName("조");
        Member member3 = new Member();
        member3.setName("조");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
         //예외발생
        //then
    }
}