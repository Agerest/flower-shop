package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.flower.shop.dto.UserDTO;
import ru.flower.shop.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/registration")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/new-user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerUserAccount(@Valid @RequestBody UserDTO userDto) {
        log.debug("Registration account, username: {}", userDto.getUsername());
        userService.registerNewUserAccount(userDto);
    }
}
