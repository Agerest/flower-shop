package ru.flower.shop.service;

import ru.flower.shop.domain.Flower;
import ru.flower.shop.dto.flower.FlowerRequestDTO;
import ru.flower.shop.dto.flower.FlowerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface FlowerService {

    void create(FlowerRequestDTO requestDTO);

    FlowerResponseDTO get(UUID id);

    void delete(UUID id);

    void update(UUID id, FlowerRequestDTO requestDTO);

    List<FlowerResponseDTO> getByCategory(UUID category);

    Flower getDomain(UUID id);
}
