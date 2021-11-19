package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Flower;

import java.util.List;
import java.util.UUID;

public interface FlowerRepository extends JpaRepository<Flower, UUID> {

    List<Flower> findAllByCategoryId(UUID categoryId);
}
