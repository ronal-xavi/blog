package com.ronal.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PublicacionRespuesta {
    private List<PublicacionDTO> contenido;
    private int numeroDePagina;
    private int medidaDePagina;
    private Long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}
