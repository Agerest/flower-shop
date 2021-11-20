package ru.flower.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flower.shop.domain.Comment;
import ru.flower.shop.domain.Flower;
import ru.flower.shop.dto.CommentDTO;
import ru.flower.shop.repository.CommentRepository;
import ru.flower.shop.service.CommentService;
import ru.flower.shop.service.FlowerService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final FlowerService flowerService;

    @Override
    public void save(CommentDTO commentDTO) {
        Flower flower = flowerService.getDomain(commentDTO.getFlowerId());
        Comment comment = new Comment(commentDTO.getText(), flower);
        commentRepository.save(comment);
    }
}
