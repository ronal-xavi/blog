package com.ronal.blog.mapper;

import com.ronal.blog.dao.CommentaryDAO;
import com.ronal.blog.dto.CommentaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {

    @Mapping(target = "publicationDTO",ignore = true)
    CommentaryDAO commentaryDtoToCommentaryDao(CommentaryDTO commentaryDTO);
    CommentaryDTO commentaryDaoToCommentaryDto(CommentaryDAO commentaryDAO);

    List<CommentaryDAO> listCommentaryDtoToCommentaryDao(List<CommentaryDTO> listCommentaryDto);
    List<CommentaryDTO> listCommentaryDaoToCommentaryDto(List<CommentaryDAO> listCommentaryDao);


}
