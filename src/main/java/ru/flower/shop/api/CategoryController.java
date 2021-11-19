package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flower.shop.dto.CategoryDTO;
import ru.flower.shop.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public List<CategoryDTO> getList() {
        log.info("Getting category list");
        return categoryService.getAll();
    }
}
