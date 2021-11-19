package ru.flower.shop.dto.flower;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class FlowerResponseDTO {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private byte[] image;
    private String categoryName;
    private List<String> comments;
}
