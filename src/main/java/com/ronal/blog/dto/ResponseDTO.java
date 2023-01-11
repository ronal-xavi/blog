package com.ronal.blog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDTO <T>{
    private Boolean success;
    private String mensaje;
    private T response;
}
