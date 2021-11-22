package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Cart;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Cart getByUserUsername(String username);
}
