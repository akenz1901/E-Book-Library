package com.booklibrary.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String authorName;

    @ManyToOne(fetch = FetchType.LAZY)
    private FavouriteBook favouriteBook;

    @ManyToOne()
    private BookCategory bookCategory;

}
