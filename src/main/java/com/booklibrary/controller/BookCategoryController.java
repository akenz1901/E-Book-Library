package com.booklibrary.controller;

import com.booklibrary.data.dto.BookCategoryDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.BookCategory;
import com.booklibrary.service.BookCategoryService;
import com.booklibrary.service.exception.BookCategoryServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping("/create")
    public BookCategory creatingCategory(@RequestBody BookCategory category){

        return bookCategoryService.createBookCategory(category);
    }
    @GetMapping("/{id}")
    public BookCategory bookCategory(@PathVariable Long id){
        return bookCategoryService.getCategory(id);
    }
    @GetMapping("/categories")
    public List<BookCategory> allCategories(){
        return bookCategoryService.getAllAvailable();
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> editingGivenCategory(@PathVariable Long id, @RequestBody BookCategoryDto bookCategoryDto){
        log.info("Request id --> {}", id);
        log.info("Book Category Dto passed -->{}", bookCategoryDto);

        BookCategory bookCategory = null;
        try{
            bookCategory = bookCategoryService.update(bookCategoryDto, id);
        }catch (BookCategoryServiceException ex){
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().body(bookCategory);
    }
    @PatchMapping("/addBook/{id}")
    public ResponseEntity<?> addBookToCategory(@PathVariable Long id, @RequestBody Book book){
        log.info("Request key -->{}", id);
        log.info("Book to post -->{}", id);

        BookCategory bookCategory = null;
        try {
            bookCategory = bookCategoryService.addBookToCategory(book, id);
        }catch(BookCategoryServiceException ex){
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().body(bookCategory);
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> removingCategory(@PathVariable Long id){
        bookCategoryService.removeCategory(id);
        return ResponseEntity.noContent().build();
    }

}
