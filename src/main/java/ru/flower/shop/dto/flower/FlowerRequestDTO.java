package ru.flower.shop.dto.flower;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class FlowerRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private UUID imageId;
    private UUID categoryId;
}
