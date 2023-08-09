package jpabook.jpashop2.domain.item;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item{

    private String artist;
    private String etc;
}
