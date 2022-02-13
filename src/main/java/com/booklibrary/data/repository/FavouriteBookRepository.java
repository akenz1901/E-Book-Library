package com.booklibrary.data.repository;

import com.booklibrary.data.model.FavouriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteBookRepository extends JpaRepository<FavouriteBook, Long>{
}
