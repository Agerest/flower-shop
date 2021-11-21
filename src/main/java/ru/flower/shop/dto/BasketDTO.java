package ru.flower.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO {

    private String flower;
    private byte[] image;
    private Integer count;
    private BigDecimal price;
}
