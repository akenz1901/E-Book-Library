package com.booklibrary.service;

import com.booklibrary.data.dto.BookDto;
import com.booklibrary.data.model.Book;
import com.booklibrary.data.repository.BookRepository;
import com.booklibrary.service.exception.BookServiceException;
import com.booklibrary.service.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Override
    public Book createBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book update(BookDto dto, Long id) throws BookServiceException {
       validateBook(dto);

       Optional<Book> foundBook= bookRepository.findById(id);
       if(foundBook.isPresent()) {
           Book book = foundBook.get();
           bookMapper.mapDtoToBook(dto, book);
           return bookRepository.save(book);
       }
       else
           throw new BookServiceException("The given id " + id + "does not exist");
        }

    @Override
    public Book retrieveASpecificBook(Long id) {

        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void removeBook(Long id) {
            bookRepository.deleteById(id);
    }

    private void validateBook(BookDto book){
            if (book == null)
                throw new NullPointerException("Pls update the book");
        }
}
