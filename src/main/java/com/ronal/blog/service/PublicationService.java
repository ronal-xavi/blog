package com.ronal.blog.service;

import com.ronal.blog.dto.PublicacionDTO;
import com.ronal.blog.dto.PublicacionRespuesta;

import java.util.List;

public interface PublicationService {
     PublicacionDTO save(PublicacionDTO dto);
     List<PublicacionDTO> list(int numeroDePagina, int medidaDePagina);
     PublicacionRespuesta listar(int numeroDePagina,int medidaDePagina,String ordenarPor,String sortDir );
     PublicacionDTO searchById(Long id);
     PublicacionDTO update(PublicacionDTO dto, Long id);
     void delete(Long id);
}
