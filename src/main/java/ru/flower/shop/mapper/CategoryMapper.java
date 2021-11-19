package ru.flower.shop.mapper;

import org.mapstruct.Mapper;
import ru.flower.shop.domain.Category;
import ru.flower.shop.dto.CategoryDTO;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDTO> toListDto(List<Category> categoryList);
}
