package ru.flower.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private UUID id;
    private String name;
}
