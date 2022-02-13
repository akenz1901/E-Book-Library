package com.booklibrary.service;

import com.booklibrary.data.dto.BookCategoryDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.BookCategory;
import com.booklibrary.service.exception.BookCategoryServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCategoryService {

    BookCategory createBookCategory(BookCategory bookCategory);

    BookCategory getCategory(Long id);

    List<BookCategory> getAllAvailable();

    BookCategory update(BookCategoryDto bookCategoryDto, Long id) throws BookCategoryServiceException;

    void removeCategory(Long l);

    BookCategory addBookToCategory(Book book, Long id) throws BookCategoryServiceException;
}
