package ru.flower.shop.service;

import org.springframework.web.multipart.MultipartFile;
import ru.flower.shop.domain.Image;

import java.util.UUID;

public interface ImageService {

    UUID create(MultipartFile image);

    Image get(UUID id);
}
