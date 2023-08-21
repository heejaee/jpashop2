package jpabook.jpashop2.domain.item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Book extends Item{

    public Book() {
    }

    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }

    private String author;
    private String isbn;
}
