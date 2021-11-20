package ru.flower.shop.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.flower.shop.dto.CommentDTO;
import ru.flower.shop.service.CommentService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody CommentDTO requestDTO) {
        log.info("Saving comment, dto: {}", requestDTO);
        commentService.save(requestDTO);
    }
}
