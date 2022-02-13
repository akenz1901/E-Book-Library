package com.booklibrary.controller;

import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.FavouriteBook;
import com.booklibrary.service.FavouriteBookService;
import com.booklibrary.service.exception.FavouriteBookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/favourite")
public class FavouriteBookController {

    @Autowired
    FavouriteBookService favouriteBookService;

    @PostMapping("/create")
    public FavouriteBook creatingFavourite(@RequestBody FavouriteBook favouriteBook){
        return favouriteBookService.createBookFavouriteList(favouriteBook);
    }
    @PatchMapping("/addBook/{id}")
    public ResponseEntity<?> addBookToFav(@PathVariable Long id, @RequestBody Book book){
        log.info("Requested favourite id -->{}", id);
        log.info("Book to be posted -->{}", book);
        FavouriteBook favouriteBook = null;
        try{
            favouriteBook = favouriteBookService.addBook(book, id);
        }catch (FavouriteBookException ex){
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().body(favouriteBook);
    }
    @GetMapping("/{id}")
    public FavouriteBook gettingFavouriteBookFolder(@PathVariable Long id){
        return favouriteBookService.getFavourite(id);
    }
}
