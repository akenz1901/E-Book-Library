package com.booklibrary.service;

import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.FavouriteBook;
import com.booklibrary.service.exception.FavouriteBookException;
import org.springframework.stereotype.Service;

@Service
public interface FavouriteBookService {

    FavouriteBook createBookFavouriteList(FavouriteBook favouriteBook);

    FavouriteBook addBook(Book book, Long id) throws FavouriteBookException;

    FavouriteBook getFavourite(Long id);
}
