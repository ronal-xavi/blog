package com.ronal.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {
    private Long idPublication;
    private String title;
    private String description;
    private String content;
    private CommentaryDTO commentaryDTO;

}
