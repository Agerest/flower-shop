package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flower.shop.domain.Category;
import ru.flower.shop.dto.CategoryDTO;
import ru.flower.shop.mapper.CategoryMapper;
import ru.flower.shop.repository.CategoryRepository;
import ru.flower.shop.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category get(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find category"));
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toListDto(categoryList);
    }
}
