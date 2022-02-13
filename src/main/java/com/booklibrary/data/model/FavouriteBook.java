package com.booklibrary.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FavouriteBook{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String label;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fav_book_id")
    private List<Book> favouriteBooks = new ArrayList<>();

    public void addBook(Book book){
        favouriteBooks.add(book);
    }
}
