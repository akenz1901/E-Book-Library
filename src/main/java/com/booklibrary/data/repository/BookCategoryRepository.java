package com.booklibrary.data.repository;


import com.booklibrary.data.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    BookCategory findByName(String name);
}
