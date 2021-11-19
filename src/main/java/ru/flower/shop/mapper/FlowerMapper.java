package ru.flower.shop.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.flower.shop.domain.Category;
import ru.flower.shop.domain.Comment;
import ru.flower.shop.domain.Flower;
import ru.flower.shop.domain.Image;
import ru.flower.shop.dto.flower.FlowerRequestDTO;
import ru.flower.shop.dto.flower.FlowerResponseDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface FlowerMapper {

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "dto.name")
    })
    Flower toDomain(FlowerRequestDTO dto, Image image, Category category);

    @Mappings(value = {
            @Mapping(target = "image", source = "flower.image.photo"),
            @Mapping(target = "categoryName", source = "flower.category.name")
    })
    FlowerResponseDTO toDto(Flower flower);

    default String mapId(UUID value) {
        return value.toString();
    }

    default List<String> mapComments(List<Comment> value) {
        return value.stream().map(Comment::getText).collect(Collectors.toList());
    }

    List<FlowerResponseDTO> toListDto(List<Flower> flowers);

    @InheritConfiguration
    void update(FlowerRequestDTO dto, Image image, Category category, @MappingTarget Flower flower);
}
