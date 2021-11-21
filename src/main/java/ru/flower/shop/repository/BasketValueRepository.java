package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Basket;
import ru.flower.shop.domain.BasketValue;

import java.util.UUID;

public interface BasketValueRepository extends JpaRepository<BasketValue, UUID> {
}
