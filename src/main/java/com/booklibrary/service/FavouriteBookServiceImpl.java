package com.booklibrary.service;

import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.FavouriteBook;
import com.booklibrary.data.repository.BookRepository;
import com.booklibrary.data.repository.FavouriteBookRepository;
import com.booklibrary.service.exception.FavouriteBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouriteBookServiceImpl implements FavouriteBookService{

    @Autowired
    private FavouriteBookRepository favouriteBookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public FavouriteBook createBookFavouriteList(FavouriteBook favouriteBook) {
        return favouriteBookRepository.save(favouriteBook);
    }

    @Override
    public FavouriteBook addBook(Book book, Long id) throws FavouriteBookException {
        validateBook(book);

        Optional<FavouriteBook> favouriteBook = favouriteBookRepository.findById(id);
        if(favouriteBook.isPresent()) {
            FavouriteBook favBook = favouriteBook.get();
            if(book.getId() == null){
                book = bookService.createBook(book);
            }
            else if(bookRepository.findById(book.getId()).isPresent()){
                book = bookService.retrieveASpecificBook(book.getId());
            }
            favBook.addBook(book);
            return favBook;
        }
        else
            throw new FavouriteBookException("Favourite book does not exist");
    }

    @Override
    public FavouriteBook getFavourite(Long id) {

        return favouriteBookRepository.findById(id).orElse(null);
    }

    private void validateBook(Book book){
        if(book == null)
            throw new NullPointerException("Book is Null, pls pass a valid book");
    }
}
