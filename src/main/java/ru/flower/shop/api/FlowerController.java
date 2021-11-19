package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.flower.shop.dto.flower.FlowerRequestDTO;
import ru.flower.shop.dto.flower.FlowerResponseDTO;
import ru.flower.shop.service.FlowerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/flower")
public class FlowerController {

    private final FlowerService flowerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody FlowerRequestDTO requestDTO) {
        log.info("Saving flower, dto: {}", requestDTO);
        flowerService.create(requestDTO);
    }

    @GetMapping("/get")
    public FlowerResponseDTO get(@RequestParam UUID id) {
        log.info("Getting flower, id: {}", id);
        return flowerService.get(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam UUID id) {
        log.info("Deleting flower, id: {}", id);
        flowerService.delete(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam UUID id, @RequestBody FlowerRequestDTO requestDTO) {
        log.info("Updating flower, id: {}, dto: {}", id, requestDTO);
        flowerService.update(id, requestDTO);
    }

    @GetMapping("/list")
    public List<FlowerResponseDTO> getList(@RequestParam(required = false) UUID categoryId) {
        log.info("Getting flower list, categoryId: {}", categoryId);
        return flowerService.getByCategory(categoryId);
    }
}
