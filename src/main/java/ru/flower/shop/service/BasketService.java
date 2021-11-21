package ru.flower.shop.service;

import ru.flower.shop.domain.Basket;
import ru.flower.shop.dto.BasketDTO;

import java.util.List;
import java.util.UUID;

public interface BasketService {

    Basket save(Basket basket);

    void add(String username, UUID flowerId);

    Integer getCount(String username);

    void clear(String username);

    void deleteOne(String username, UUID flowerId);

    List<BasketDTO> get(String username);
}
