package ru.flower.shop.service;

import ru.flower.shop.domain.User;
import ru.flower.shop.dto.UserDTO;

public interface UserService {

    User registerNewUserAccount(UserDTO userDto);

    User findByUsername(String username);
}
