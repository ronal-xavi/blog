package com.ronal.blog.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PublicationResponseDTO {
    private List<PublicationDTO> contenido;
    private int pageNumber;
    private int pageSize;
    private Long totalElementos;
    private int totalPaginas;
    private boolean ultima;
}
