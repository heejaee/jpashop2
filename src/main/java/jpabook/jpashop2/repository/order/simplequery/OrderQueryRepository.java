package jpabook.jpashop2.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
               //java/jpabook/jpashop2/repository/order/simplequery/OrderQueryDto.java
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos(){
        return em.createQuery(
                "select new jpabook.jpashop2.repository.order.simplequery.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDto.class).getResultList();
    }
}
