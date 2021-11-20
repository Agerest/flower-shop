package ru.flower.shop.dto;

import lombok.Data;
import ru.flower.shop.annotation.PasswordMatches;
import ru.flower.shop.annotation.ValidEmail;

import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class UserDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    @NotEmpty
    @ValidEmail
    private String email;
}
