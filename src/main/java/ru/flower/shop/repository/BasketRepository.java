package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Basket;
import ru.flower.shop.domain.Flower;

import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, UUID> {
}
