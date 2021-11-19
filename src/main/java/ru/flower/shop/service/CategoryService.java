package ru.flower.shop.service;

import ru.flower.shop.domain.Category;
import ru.flower.shop.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category get(UUID id);

    List<CategoryDTO> getAll();
}
