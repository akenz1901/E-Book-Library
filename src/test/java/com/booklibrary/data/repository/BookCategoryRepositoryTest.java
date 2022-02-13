package com.booklibrary.data.repository;

import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.BookCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class BookCategoryRepositoryTest {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @BeforeEach
    void setUp(){
    }

    @Test
    @Transactional
    void createBookCategory(){
        Book book = new Book();
        book.setTitle("The big ocean");
        book.setDescription("The big ocean is story of two couples who had their honeymoon on the sea");
        book.setAuthorName("Michael Faraye");

        BookCategory genre = new BookCategory("Fiction",
                "Fiction books contain a story that the author made up.",book);
        log.info("Before Saving Category --> {}", genre);
        bookCategoryRepository.save(genre);
        log.info("After Saving Category --> {}", genre);
        assertThat(genre).isNotNull();
        assertThat(genre.getBookList()).isNotEmpty().hasSize(1);
        assertThat(genre.getId()).isEqualTo(1);

    }

    @Test
    void categoryCanBeFoundByName(){
        String name = bookCategoryRepository.findByName("Fiction").getName();
        assertThat(name).endsWith("tion");
    }

}