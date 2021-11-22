package ru.flower.shop.service;

import ru.flower.shop.domain.Cart;
import ru.flower.shop.dto.CartDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {

    Cart save(Cart cart);

    void add(String username, UUID flowerId);

    Integer getCount(String username);

    void clear(String username);

    void deleteOne(String username, UUID flowerId);

    List<CartDTO> get(String username);
}
