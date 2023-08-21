package jpabook.jpashop2.domain.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop2.domain.Address;
import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.domain.Order;
import jpabook.jpashop2.domain.OrderStatus;
import jpabook.jpashop2.exception.NotEnoughStockException;
import jpabook.jpashop2.domain.item.Book;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.OrderRepository;
import jpabook.jpashop2.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member m = createMember("박희재", new Address("서울", "잠실대교", "123"));

        Book book = createBook("퍼거슨", 1000, 10);
        int orderCount =3;
        //when
        Long orderId = orderService.order(m.getId(), book.getId(), orderCount);
        Order findOrder = orderRepository.findOne(orderId);

        //then
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(1).isEqualTo(findOrder.getOrderItems().size());
        assertThat(1000*orderCount).isEqualTo(findOrder.getTotalPrice());
        assertThat(7).isEqualTo(book.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {

        //given
        Member member = createMember("조재", new Address("고덩","다리","456"));
        Item book = createBook("시골 JPA",1000,10);

        int orderCount =11;
        //when

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount);
        });
    }


    @Test
    public void 주문취소() throws Exception{

        //given
        Member member = createMember("조재", new Address("고덩","다리","456"));
        Item book = createBook("시골 JPA",1000,10);
        int orderCount =3;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);
        Order findOrder = orderRepository.findOne(orderId);

        //then
        assertThat(10).isEqualTo(book.getStockQuantity());
        assertThat(OrderStatus.CANCEL).isEqualTo(findOrder.getStatus());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name, Address address) {
        Member m = new Member();
        m.setName(name);
        m.setAddress(address);
        em.persist(m);
        return m;
    }

}