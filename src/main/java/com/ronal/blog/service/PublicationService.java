package com.ronal.blog.service;

import com.ronal.blog.dto.PublicationDTO;
import com.ronal.blog.dto.PublicationResponse;
import com.ronal.blog.dto.ResponseDTO;

import java.util.List;

public interface PublicationService {

     ResponseDTO<PublicationDTO> save(PublicationDTO dto);
     List<PublicationDTO> listaRonal ();
     List<PublicationDTO> list(int numeroDePagina, int medidaDePagina);
     PublicationResponse listar(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir );
     PublicationDTO searchById(Long id);
     PublicationDTO update(PublicationDTO dto, Long id);
     void delete(Long id);
}
