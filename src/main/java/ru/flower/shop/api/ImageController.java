package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.flower.shop.service.ImageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@RequestPart("image") MultipartFile image) {
        log.info("Creating image, file: {}", image);
        return imageService.create(image).toString();
    }
}
