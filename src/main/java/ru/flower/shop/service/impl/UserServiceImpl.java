package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flower.shop.domain.Basket;
import ru.flower.shop.domain.User;
import ru.flower.shop.dto.UserDTO;
import ru.flower.shop.mapper.UserMapper;
import ru.flower.shop.repository.UserRepository;
import ru.flower.shop.service.BasketService;
import ru.flower.shop.service.RoleService;
import ru.flower.shop.service.UserService;
import ru.flower.shop.utils.RoleConstant;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final BasketService basketService;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalStateException("There is an account with that username: " + userDto.getUsername());
        }
        User user = userMapper.toEntity(userDto);
        Basket basket = basketService.save(new Basket());
        user.setBasket(basket);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(roleService.getByName(RoleConstant.USER_ROLE)));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Could not find user"));
    }
}
