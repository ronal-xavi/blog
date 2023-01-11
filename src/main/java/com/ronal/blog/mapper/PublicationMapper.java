package com.ronal.blog.mapper;

import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.dto.PublicationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    PublicationDAO publicationDtoToPublicationDao(PublicationDTO publicationDTO);
    PublicationDTO publicationDaoToPublicationDto(PublicationDAO publicationDAO);

    List<PublicationDAO> lstToDao(List<PublicationDTO> listDTO);

    List<PublicationDTO>lstToDto(List<PublicationDAO> listDAO);

}
