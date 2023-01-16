package com.ronal.blog.mapper;

import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.dto.PublicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    @Mapping(target = "commentaryDAO",ignore = true)
    PublicationDAO publicationDtoToPublicationDao(PublicationDTO publicationDTO);

    @Mapping(target = "commentaryDTO",ignore = true)
    PublicationDTO publicationDaoToPublicationDto(PublicationDAO publicationDAO);

    List<PublicationDAO> listDtoToListDao(List<PublicationDTO> listDTO);

    List<PublicationDTO> listDaoToListDto(List<PublicationDAO> listDAO);

}
