package com.booklibrary.service;

import com.booklibrary.data.dto.BookCategoryDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.model.BookCategory;
import com.booklibrary.data.repository.BookCategoryRepository;
import com.booklibrary.service.exception.BookCategoryServiceException;
import com.booklibrary.service.mapper.BookCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryImpl implements BookCategoryService{
    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookService bookService;


    @Override
    public BookCategory createBookCategory(BookCategory bookCategory) {
        return bookCategoryRepository.save(bookCategory);
    }

    @Override
    public BookCategory getCategory(Long id) {
        return bookCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookCategory> getAllAvailable() {
        return bookCategoryRepository.findAll();
    }

    @Override
    public BookCategory update(BookCategoryDto bookCategoryDto, Long id) throws BookCategoryServiceException {
        checkDtoNotNull(bookCategoryDto);

        Optional<BookCategory> category = bookCategoryRepository.findById(id);
        if(category.isPresent()){
            BookCategory bookCategory = category.get();
            bookCategoryMapper.mapDtoToBookCategory(bookCategoryDto, bookCategory);
            return bookCategoryRepository.save(bookCategory);
        }
        else
            throw new BookCategoryServiceException("Book category with id number" + id + "does not exit");
    }

    @Override
    public void removeCategory(Long id) {
        bookCategoryRepository.deleteById(id);
    }

    @Override
    public BookCategory addBookToCategory(Book book, Long id) throws BookCategoryServiceException {
        validateBook(book);
        Optional<BookCategory> foundCategory = bookCategoryRepository.findById(id);
        if(foundCategory.isPresent()){
            BookCategory bookCategory = foundCategory.get();
            if(book.getId() == null) {
                book = bookService.createBook(book);
            }
            else if(bookCategoryRepository.findById(book.getId()).isPresent()){
                book = bookService.retrieveASpecificBook(book.getId());
            }
            bookCategory.addBook(book);
            return bookCategory;
        }
        else
            throw new BookCategoryServiceException("Category does not exist");

    }

    void checkDtoNotNull(BookCategoryDto bookCategoryDtoDto){
        if(bookCategoryDtoDto == null)
            throw new NullPointerException("Pls update the category");
    }
    void validateBook(Book book){
        if(book == null)
            throw new NullPointerException("Book cannot be none");
    }


}
