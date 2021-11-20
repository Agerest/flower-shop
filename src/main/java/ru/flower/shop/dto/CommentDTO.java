package ru.flower.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CommentDTO {

    @NotNull
    private UUID flowerId;

    @NotEmpty
    private String text;
}
