package com.booklibrary.service.mapper;

import com.booklibrary.data.dto.BookCategoryDto;
import com.booklibrary.data.model.BookCategory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookCategoryMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToBookCategory(BookCategoryDto bookCategoryDto,@MappingTarget  BookCategory bookCategory);
}
