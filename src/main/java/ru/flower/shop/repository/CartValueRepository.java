package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.CartValue;

import java.util.UUID;

public interface CartValueRepository extends JpaRepository<CartValue, UUID> {
}
