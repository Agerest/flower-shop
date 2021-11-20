package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flower.shop.domain.Role;
import ru.flower.shop.repository.RoleRepository;
import ru.flower.shop.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
}
