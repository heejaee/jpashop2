package jpabook.jpashop2.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop2.domain.Category;
import jpabook.jpashop2.domain.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
//    객체지향적으로 생각하면
//데이터를 가지고 있는 쪽이 비즈니스메서드가 있는게 가장 좋다 왜냐 응집력이 있기 때문에
    // stock 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //stock 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
