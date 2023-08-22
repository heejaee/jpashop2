package jpabook.jpashop2;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop2.domain.Address;
import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.domain.item.Book;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.ItemRepository;
import jpabook.jpashop2.service.ItemService;
import jpabook.jpashop2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final MemberService memberService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setName("박희재");
        member.setAddress(new Address("서울","송파","123"));

        Book book= new Book();
        book.setId(1L);
        book.setName("자바의 정석");
        book.setPrice(10000);
        book.setStockQuantity(10);

        Book book2= new Book();
        book2.setId(2L);
        book2.setName("수능 특강");
        book2.setPrice(15000);
        book2.setStockQuantity(20);

        memberService.join(member);
        itemService.saveItem(book);
        itemService.saveItem(book2);

        //오류 발생
//        itemRepository.save(new Book("김영한","한빛"));
//        itemRepository.save(new Book("박희재","가천"));
    }

}
