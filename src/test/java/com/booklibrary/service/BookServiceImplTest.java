package com.booklibrary.service;

import com.booklibrary.data.dto.BookDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.repository.BookRepository;
import com.booklibrary.service.exception.BookServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookServiceImplTest {

    @Autowired
    private BookService bookServiceImpl;

    @Autowired
    private BookRepository bookRepository;

    Book book;
    @BeforeEach
    void setUp(){

    }

    @Test
    @Transactional
    void createBookWithService(){
        book = new Book();
        book.setTitle("The heart of the Zoro");
        book.setAuthorName("Kelvin Lamitha");
        book.setDescription("It is a true life story where a man named zoro got his family arrested for their wrong doings");
        log.info("Before saving book -->{}", book);
        Book savedBook = bookServiceImpl.createBook(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isEqualTo(1);
        log.info("After saving book -->{}", book);

    }

    @Test
    void checkAllAvailableBooks(){
        List<Book> bookList= new ArrayList<>();
        log.info("Before finding books -->{}", bookList);

        bookList = bookServiceImpl.getAllBooks();

        assertThat(bookList).isNotEmpty();
    }
    @Test
    @Transactional
    void bookCanBeUpdated() throws BookServiceException {
        Book foundBook = bookRepository.findById(1L).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("The heart of the Zoro");
        log.info("Before book got updated --> {}", foundBook);

        BookDto tempBook = new BookDto();
        tempBook.setDescription("A man who got his dream happen through innovation");
        tempBook.setAuthorName("John Olawale");
        tempBook.setTitle("The Golden Top");

        foundBook = bookServiceImpl.update(tempBook, foundBook.getId());
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("The Golden Top");
        assertThat(foundBook.getId()).isEqualTo(1);
        log.info("After book got updated --> {}", foundBook);

    }
    @Test
    void singleBookCanBeRetrievedById(){
        Book found  = bookServiceImpl.retrieveASpecificBook(1L);

        log.info("Book found --> {}", found);
        assertThat(found).isNotNull();
    }

    @Test
    void bookBookCanBeDeleted(){
        Book book = bookServiceImpl.retrieveASpecificBook(1L);

        assertThat(book).isNotNull();
        log.info("Before deleting book --> {}", book);
        bookServiceImpl.removeBook(book.getId());

        book = bookServiceImpl.retrieveASpecificBook(1L);
        assertThat(book).isNull();
        log.info("After deleting book --> {}", book);

    }

}