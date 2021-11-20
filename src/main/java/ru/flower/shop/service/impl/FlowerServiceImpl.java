package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flower.shop.domain.Category;
import ru.flower.shop.domain.Flower;
import ru.flower.shop.domain.Image;
import ru.flower.shop.dto.flower.FlowerRequestDTO;
import ru.flower.shop.dto.flower.FlowerResponseDTO;
import ru.flower.shop.mapper.FlowerMapper;
import ru.flower.shop.repository.FlowerRepository;
import ru.flower.shop.service.CategoryService;
import ru.flower.shop.service.FlowerService;
import ru.flower.shop.service.ImageService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;
    private final FlowerMapper flowerMapper;
    private final ImageService imageService;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public void create(FlowerRequestDTO requestDTO) {
        Image image = imageService.get(requestDTO.getImageId());
        Category category = categoryService.get(requestDTO.getCategoryId());
        Flower flower = flowerMapper.toDomain(requestDTO, image, category);
        flowerRepository.save(flower);
    }

    @Override
    public FlowerResponseDTO get(UUID id) {
        Flower flower = getDomain(id);
        return flowerMapper.toDto(flower);
    }

    @Override
    public Flower getDomain(UUID id) {
        return flowerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Could not find flower"));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        flowerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(UUID id, FlowerRequestDTO requestDTO) {
        Flower flower = getDomain(id);
        Image image = imageService.get(requestDTO.getImageId());
        Category category = categoryService.get(requestDTO.getCategoryId());
        flowerMapper.update(requestDTO, image, category, flower);
    }

    @Override
    public List<FlowerResponseDTO> getByCategory(UUID category) {
        List<Flower> flowerList;
        if (category == null) {
            flowerList = flowerRepository.findAll();
        } else {
            flowerList = flowerRepository.findAllByCategoryId(category);
        }
        return flowerMapper.toListDto(flowerList);
    }
}
