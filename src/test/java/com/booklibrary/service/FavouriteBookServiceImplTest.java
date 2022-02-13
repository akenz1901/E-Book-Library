package com.booklibrary.service;

import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.FavouriteBook;
import com.booklibrary.data.repository.FavouriteBookRepository;
import com.booklibrary.service.exception.FavouriteBookException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class FavouriteBookServiceImplTest {

    @Autowired
    private FavouriteBookService favouriteBookService;

    @Autowired
    private FavouriteBookRepository favouriteBookRepository;

    private FavouriteBook favouriteBook;
    private Book book;
    @BeforeEach
    void setUp(){
        favouriteBook = new FavouriteBook();
        book = new Book();
        book.setTitle("Mysterious Stunt");
        book.setDescription("The great gient who snicked into a hole");
        book.setAuthorName("Jisunya Yinju");
    }

    @Test
    void favouriteBookListCanBeCreated(){
        log.info("Before saving favourite -->{}", favouriteBook);
        favouriteBook = favouriteBookService.createBookFavouriteList(favouriteBook);
        assertThat(favouriteBook).isNotNull();
        assertThat(favouriteBook.getId()).isNotNull();
        assertThat(favouriteBook.getFavouriteBooks()).isNotNull();
        log.info("Before saving favourite -->{}", favouriteBook);

    }
    @Test
    void bookCanBeAddedToFavouriteBookList() throws FavouriteBookException {
        assertThat(book).isNotNull();
        assertThat(book.getId()).isNull();
        assertThat(favouriteBook.getId()).isNull();

        favouriteBook = favouriteBookRepository.findById(1L).orElse(null);

        assertThat(favouriteBook).isNotNull();
        assertThat(favouriteBook.getId()).isNotNull();
        log.info("Before adding favourite book --> {}", favouriteBook.getFavouriteBooks());

        favouriteBook = favouriteBookService.addBook(book, 1L);
        assertThat(favouriteBook.getFavouriteBooks()).isNotEmpty();
        assertThat(favouriteBook.getFavouriteBooks()).contains(book);
        log.info("After adding favourite book --> {}", favouriteBook.getFavouriteBooks());
    }
}