package com.ronal.blog.mapper;

import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.dto.PublicationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    PublicationDAO publicationDtoToPublicationDao(PublicationDTO publicationDTO);
    PublicationDTO publicationDaoToPublicationDto(PublicationDAO publicationDAO);

    List<PublicationDAO> listDtoToListDao(List<PublicationDTO> listDTO);

    List<PublicationDTO> listDaoToListDto(List<PublicationDAO> listDAO);

}
