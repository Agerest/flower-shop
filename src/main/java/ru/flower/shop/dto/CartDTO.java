package ru.flower.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private String flower;
    private byte[] image;
    private Integer count;
    private BigDecimal price;
    private String flowerId;
}
