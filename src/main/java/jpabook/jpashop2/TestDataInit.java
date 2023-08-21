package jpabook.jpashop2;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop2.domain.item.Book;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.ItemRepository;
import jpabook.jpashop2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemService.saveItem(new Book("김영한","한빛"));
        itemService.saveItem(new Book("박희재","가천"));

        //오류 발생
//        itemRepository.save(new Book("김영한","한빛"));
//        itemRepository.save(new Book("박희재","가천"));
    }

}
