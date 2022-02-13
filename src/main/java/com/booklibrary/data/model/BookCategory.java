package com.booklibrary.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Book> bookList = new ArrayList<>();

    public BookCategory(String name, String description, Book book){
        this.name = name;
        this.description = description;
        addBook(book);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

}
