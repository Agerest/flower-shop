package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.flower.shop.domain.Image;
import ru.flower.shop.repository.ImageRepository;
import ru.flower.shop.service.ImageService;
import ru.flower.shop.utils.ImageUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @SneakyThrows
    public UUID create(MultipartFile image) {
        byte[] photo = ImageUtils.photoConvert(image);
        return imageRepository.save(new Image(photo)).getId();
    }

    @Override
    public Image get(UUID id) {
        return imageRepository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find image"));
    }
}
