package com.ronal.blog.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentaryDTO {
    private Integer idCommentary;
    private String nombre;
    private String email;
    private String body;
    private PublicationDTO publicationDTO;
}
