package com.booklibrary.service.exception;

public class FavouriteBookException extends BookCategoryServiceException{
    public FavouriteBookException() {
        super();
    }

    public FavouriteBookException(String message) {
        super(message);
    }
}
