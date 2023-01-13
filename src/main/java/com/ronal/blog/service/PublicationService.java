package com.ronal.blog.service;

import com.ronal.blog.dto.PublicationDTO;
import com.ronal.blog.dto.PublicationResponseDTO;
import com.ronal.blog.dto.ResponseDTO;

public interface PublicationService {

     PublicationResponseDTO listPublications(int numeroDePagina, int pageSize, String sortBy, String sortDir);
     PublicationDTO searchById(Long id);
     ResponseDTO<PublicationDTO> save(PublicationDTO dto);
     PublicationDTO update(PublicationDTO dto, Long id);
     void delete(Long id);
}
