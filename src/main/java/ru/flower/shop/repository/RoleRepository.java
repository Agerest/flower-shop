package ru.flower.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flower.shop.domain.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);
}
