package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Basket;
import ru.flower.shop.domain.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
