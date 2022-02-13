package com.booklibrary.controller;

import com.booklibrary.data.dto.BookDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.service.BookService;
import com.booklibrary.service.exception.BookServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public Book creatingBook(@RequestBody Book book){
        log.info("Book before saving --> {}", book);
        return bookService.createBook(book);
    }
    @GetMapping("/all")
    public List<Book> gettingAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book gettingABook(@PathVariable Long id){
        return bookService.retrieveASpecificBook(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> editingBook(@PathVariable Long id,@RequestBody BookDto bookDto){

        log.info("Request id --> {}", id);
        log.info("Book dto details --> {}", bookDto);
        Book book = null;
        try{
            book = bookService.update(bookDto, id);
        }catch(BookServiceException e){
            log.info("Book does not exit --> {}", e.getLocalizedMessage());
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(book);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deletingBook(@PathVariable Long id){
        bookService.removeBook(id);
        return ResponseEntity.noContent().build();
    }
}
