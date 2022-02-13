package com.booklibrary.service;

import com.booklibrary.data.dto.BookCategoryDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.BookCategory;
import com.booklibrary.service.exception.BookCategoryServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Slf4j
class BookCategoryImplTest {

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private BookService bookServiceImpl;

    private Book book;

    @BeforeEach
    void setUp(){
        book = new Book();
        book.setTitle("Industrial Structure");
        book.setDescription("A book that prepare you for the great technology industry");
        book.setAuthorName("Dapo Abiodun");

    }

    @Test
    void bookCategoryCanBeCreated(){
        BookCategory bookCategory = new BookCategory("Drama",
                "Stories in verse or prose, usually for theatrical perfomance, where conflicts and emotion are expressed through dialogue.",
                book);
        assertThat(bookCategory.getId()).isNull();
        log.info("Book category before being saved --> {}", bookCategory);
        assertThat(bookCategory).isNotNull();
        bookCategory = bookCategoryService.createBookCategory(bookCategory);
        assertThat(bookCategory.getId()).isEqualTo(1);
        assertThat(bookCategory.getBookList()).isNotEmpty();
        assertThat(bookCategory.getName()).isEqualTo("Drama");
        log.info("Book category after being saved --> {}",bookCategory);

    }
    @Test
    void categoryCanBeFetched(){
        BookCategory foundCategory = bookCategoryService.getCategory(1L);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Drama");
    }

    @Test
    void allAvailableCategories(){
        List<BookCategory> categories = bookCategoryService.getAllAvailable();
        log.info("The list od bookCategories --> {}", categories);
        assertThat(categories).isNotEmpty();
        assertThat(categories).isNotNull();
    }

    @Test
    void categoryCanBeModified() throws BookCategoryServiceException {
        BookCategoryDto bookCategoryDto = new BookCategoryDto();
        bookCategoryDto.setName("Horror");
        bookCategoryDto.setDescription("The goal of this category is to scare the reader about a specific story.");

        BookCategory givenBook = bookCategoryService.getCategory(1L);
        log.info("Before modifying the category -->{}", givenBook);
        assertThat(givenBook).isNotNull();

        givenBook = bookCategoryService.update(bookCategoryDto, givenBook.getId());
        log.info("After updating the category --> {}", givenBook);
        assertThat(givenBook).isNotNull();
        assertThat(givenBook.getName()).isEqualTo("Horror");
    }
    @Test
    void modifyingWithNonExistingBookCategoryThrowsAnException(){
        assertThrows(BookCategoryServiceException.class, ()-> bookCategoryService.update(new BookCategoryDto(),6L));
    }
    @Test
    void categoryCanBeDeleted(){
        BookCategory bookCategory = bookCategoryService.getCategory(1L);
        log.info("Before deleting book category --> {}", bookCategory);
        assertThat(bookCategory).isNotNull();

        bookCategoryService.removeCategory(1L);
        bookCategory = bookCategoryService.getCategory(bookCategory.getId());
        assertThat(bookCategory).isNull();
        log.info("After deleting book category --> {}", bookCategory);
    }

    @Test
    void bookCanBeAddedToCategory() throws BookCategoryServiceException {
        Book newBook = new Book();
        newBook.setAuthorName("Panty Spark");
        newBook.setTitle("The Rose At My Door");
        newBook.setDescription("The is a romance book where a friend staying at the next building to Julia which they fell in love with each other");

        BookCategory existingCategory = bookCategoryService.getCategory(1L);
        log.info("Before adding book to category --> {}", existingCategory);
        assertThat(existingCategory).isNotNull();

        existingCategory = bookCategoryService.addBookToCategory(newBook, 1L);
        log.info("After adding book to category --> {}", existingCategory);
        assertThat(existingCategory.getBookList()).isNotEmpty();
        assertThat(existingCategory.getBookList()).contains(newBook);
        assertThat(existingCategory.getBookList().get(1).getId()).isNotNull();

    }
}