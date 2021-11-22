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
import ru.flower.shop.dto.CartDTO;
import ru.flower.shop.service.CartService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public void add(@AuthenticationPrincipal UserDetails userDetails,
                    @RequestParam UUID flowerId) {
        log.info("Adding flower to cart, flowerId: {}", flowerId);
        cartService.add(userDetails.getUsername(), flowerId);
    }

    @GetMapping("/count")
    public Integer getCount(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting cart count");
        return cartService.getCount(userDetails.getUsername());
    }

    @DeleteMapping("/delete")
    public void delete(@AuthenticationPrincipal UserDetails userDetails,
                       @RequestParam UUID flowerId) {
        log.info("Deleting flower from cart, flowerId: {}", flowerId);
        cartService.deleteOne(userDetails.getUsername(), flowerId);
    }

    @DeleteMapping("/delete/all")
    public void clear(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Clearing cart");
        cartService.clear(userDetails.getUsername());
    }

    @GetMapping("/get")
    public List<CartDTO> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting cart values list");
        return cartService.get(userDetails.getUsername());
    }
}
