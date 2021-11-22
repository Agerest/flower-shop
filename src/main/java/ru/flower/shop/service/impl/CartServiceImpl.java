package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flower.shop.domain.Cart;
import ru.flower.shop.domain.CartValue;
import ru.flower.shop.domain.Flower;
import ru.flower.shop.dto.CartDTO;
import ru.flower.shop.repository.CartRepository;
import ru.flower.shop.repository.CartValueRepository;
import ru.flower.shop.service.CartService;
import ru.flower.shop.service.FlowerService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final FlowerService flowerService;
    private final CartValueRepository cartValueRepository;

    @Transactional
    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void add(String username, UUID flowerId) {
        Cart cart = cartRepository.getByUserUsername(username);
        Flower flower = flowerService.getDomain(flowerId);
        CartValue value = cart.getValues().stream()
                .filter(cartValue -> cartValue.getFlower().equals(flower))
                .findFirst()
                .orElse(new CartValue(flower, cart));
        value.increment();
        cartValueRepository.save(value);
    }

    @Transactional
    @Override
    public Integer getCount(String username) {
        return cartRepository.getByUserUsername(username)
                .getValues()
                .stream()
                .map(CartValue::getCount)
                .reduce(0, Integer::sum);
    }

    @Transactional
    @Override
    public void clear(String username) {
        List<CartValue> values = cartRepository.getByUserUsername(username).getValues();
        cartValueRepository.deleteAll(values);
    }

    @Transactional
    @Override
    public void deleteOne(String username, UUID flowerId) {
        Cart cart = cartRepository.getByUserUsername(username);
        Flower flower = flowerService.getDomain(flowerId);
        CartValue value = cart.getValues().stream()
                .filter(cartValue -> cartValue.getFlower().equals(flower))
                .findFirst()
                .orElse(null);
        if (value == null) {
            return;
        }
        if (value.getCount() == 1) {
            cartValueRepository.delete(value);
            return;
        }
        value.decrement();
        cartValueRepository.save(value);
    }

    @Transactional
    @Override
    public List<CartDTO> get(String username) {
        Cart cart = cartRepository.getByUserUsername(username);
        return cart.getValues().stream()
                .map(value -> {
                    Flower flower = value.getFlower();
                    return new CartDTO(
                            flower.getName(), flower.getImage().getPhoto(), value.getCount(),
                            flower.getPrice(), flower.getId().toString());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BigDecimal getSum(String username) {
        Cart cart = cartRepository.getByUserUsername(username);
        return cart.getValues().stream()
                .map(value -> value.getFlower().getPrice().multiply(BigDecimal.valueOf(value.getCount())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
