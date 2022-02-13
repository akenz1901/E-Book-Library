package com.booklibrary.data.repository;

import com.booklibrary.data.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void createBook(){
        Book book = new Book();
        book.setTitle("Impactful Innovation");
        book.setDescription("The way of getting the best innovation for the community");
        book.setAuthorName("Boston Clinton");
        log.info("Before saving book --> {}", book);
        bookRepository.save(book);
        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(1);
        assertThat(book.getAuthorName()).isEqualTo("Boston Clinton");
        log.info("After saving book --> {}", book);
    }
}