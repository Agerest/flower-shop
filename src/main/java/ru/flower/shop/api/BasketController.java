package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.flower.shop.dto.BasketDTO;
import ru.flower.shop.service.BasketService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/basket")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/add")
    public void add(@AuthenticationPrincipal UserDetails userDetails,
                    @RequestParam UUID flowerId) {
        log.info("Adding flower to basket, flowerId: {}", flowerId);
        basketService.add(userDetails.getUsername(), flowerId);
    }

    @GetMapping("/count")
    public Integer getCount(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting basket count");
        return basketService.getCount(userDetails.getUsername());
    }

    @DeleteMapping("/delete")
    public void delete(@AuthenticationPrincipal UserDetails userDetails,
                       @RequestParam UUID flowerId) {
        log.info("Deleting flower from basket, flowerId: {}", flowerId);
        basketService.deleteOne(userDetails.getUsername(), flowerId);
    }

    @DeleteMapping("/delete/all")
    public void clear(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Clearing basket");
        basketService.clear(userDetails.getUsername());
    }

    @GetMapping("/get")
    public List<BasketDTO> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting basket values list");
        return basketService.get(userDetails.getUsername());
    }
}
