package ru.flower.shop.mapper;

import org.mapstruct.Mapper;
import ru.flower.shop.domain.User;
import ru.flower.shop.dto.UserDTO;

@Mapper
public interface UserMapper {

    User toEntity(UserDTO userDTO);
}
