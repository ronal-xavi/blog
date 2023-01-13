package com.ronal.blog.controller;

import com.ronal.blog.dto.PublicationDTO;
import com.ronal.blog.dto.PublicationResponseDTO;
import com.ronal.blog.dto.ResponseDTO;
import com.ronal.blog.exceptions.RequestException;
import com.ronal.blog.service.PublicationService;

import static com.ronal.blog.util.Constantes.*;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/publication")
public class PublicacionController {
    private final PublicationService publicationService;

    @GetMapping("/listPublications")
    public PublicationResponseDTO listarPublicaciones(@RequestParam(value = "nro", defaultValue = "0", required = false) int numeroDePagina,
                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaDePagina,
                                                      @RequestParam(value = "sortBy", defaultValue = "idPublication", required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return publicationService.listPublications(numeroDePagina, medidaDePagina, sortBy, sortDir);
    }


    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<PublicationDTO>> save(@RequestBody PublicationDTO dto) {

        if (StringUtils.isEmpty(dto.getTitle())) {
            throw new RequestException(BAD_REQUEST_TITLE, "C-50", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(dto.getDescription())) {
            throw new RequestException(BAD_REQUEST_DESCRIPTION, "C-50", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(dto.getContent())) {
            throw new RequestException(BAD_REQUEST_CONTENT, "C-50", HttpStatus.BAD_REQUEST);
        }

        ResponseDTO<PublicationDTO> salida = publicationService.save(dto);

        return new ResponseEntity<>(salida, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<PublicationDTO> obtenerPublicacionPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.searchById(id));
    }

    @PutMapping("/actualizarPublicacion/{id}")
    public ResponseEntity<PublicationDTO> actualizarPublicacion(@RequestBody PublicationDTO dto,
                                                                @PathVariable("id") Long id) {
        PublicationDTO publicacionRespuesta = publicationService.update(dto, id);

        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        publicationService.delete(id);
        return new ResponseEntity<>("PUBLICACIÓN ELIMINADA", HttpStatus.OK);
    }
}
