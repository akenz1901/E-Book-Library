package com.booklibrary.service;

import com.booklibrary.data.dto.BookDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.service.exception.BookServiceException;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    List<Book> getAllBooks();

    Book update(BookDto dto, Long id) throws BookServiceException;

    Book retrieveASpecificBook(Long id);

    void removeBook(Long id);
}
