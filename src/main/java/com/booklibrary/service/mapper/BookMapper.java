package com.booklibrary.service.mapper;

import com.booklibrary.data.dto.BookDto;
import com.booklibrary.data.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToBook(BookDto bookDto, @MappingTarget Book book);
}
