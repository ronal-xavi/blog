package com.ronal.blog.service.impl;

import com.ronal.blog.dao.CommentaryDAO;
import com.ronal.blog.dto.CommentaryDTO;
import com.ronal.blog.mapper.CommentaryMapper;
import com.ronal.blog.repository.CommentaryRepository;
import com.ronal.blog.service.CommentaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;
    private final CommentaryMapper commentaryMapper;

    @Override
    public CommentaryDTO saveCommentary(CommentaryDTO commentaryDTO) {
        CommentaryDAO commentaryDAO = commentaryRepository.save(commentaryMapper.commentaryDtoToCommentaryDao(commentaryDTO)) ;
        return commentaryMapper.commentaryDaoToCommentaryDto(commentaryDAO);
    }
}
