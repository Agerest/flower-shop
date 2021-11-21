package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flower.shop.domain.Basket;
import ru.flower.shop.domain.BasketValue;
import ru.flower.shop.domain.Flower;
import ru.flower.shop.dto.BasketDTO;
import ru.flower.shop.repository.BasketRepository;
import ru.flower.shop.repository.BasketValueRepository;
import ru.flower.shop.service.BasketService;
import ru.flower.shop.service.FlowerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final FlowerService flowerService;
    private final BasketValueRepository basketValueRepository;

    @Transactional
    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    @Transactional
    @Override
    public void add(String username, UUID flowerId) {
        Basket basket = basketRepository.getByUserUsername(username);
        Flower flower = flowerService.getDomain(flowerId);
        BasketValue value = basket.getValues().stream()
                .filter(basketValue -> basketValue.getFlower().equals(flower))
                .findFirst()
                .orElse(new BasketValue(flower, basket));
        value.increment();
        basketValueRepository.save(value);
    }

    @Transactional
    @Override
    public Integer getCount(String username) {
        return basketRepository.getByUserUsername(username)
                .getValues()
                .stream()
                .map(BasketValue::getCount)
                .reduce(0, Integer::sum);
    }

    @Transactional
    @Override
    public void clear(String username) {
        List<BasketValue> values = basketRepository.getByUserUsername(username).getValues();
        basketValueRepository.deleteAll(values);
    }

    @Transactional
    @Override
    public void deleteOne(String username, UUID flowerId) {
        Basket basket = basketRepository.getByUserUsername(username);
        Flower flower = flowerService.getDomain(flowerId);
        BasketValue value = basket.getValues().stream()
                .filter(basketValue -> basketValue.getFlower().equals(flower))
                .findFirst()
                .orElse(null);
        if (value == null) {
            return;
        }
        if (value.getCount() == 1) {
            basketValueRepository.delete(value);
            return;
        }
        value.decrement();
        basketValueRepository.save(value);
    }

    @Transactional
    @Override
    public List<BasketDTO> get(String username) {
        Basket basket = basketRepository.getByUserUsername(username);
        return basket.getValues().stream()
                .map(value -> {
                    Flower flower = value.getFlower();
                    return new BasketDTO(flower.getName(), flower.getImage().getPhoto(), value.getCount(), flower.getPrice());
                })
                .collect(Collectors.toList());
    }
}
