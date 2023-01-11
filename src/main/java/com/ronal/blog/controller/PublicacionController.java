package com.ronal.blog.controller;

import com.ronal.blog.dto.PublicacionDTO;
import com.ronal.blog.dto.PublicacionRespuesta;
import com.ronal.blog.service.PublicationService;
import static com.ronal.blog.util.AppConstantes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {
    @Autowired
    private PublicationService publicationService;


    @GetMapping("/listarPublicaciones")
    public List<PublicacionDTO> listarPublicaciones(@RequestParam(value = "nro", defaultValue = "0",required = false) int numeroDePagina,
                                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int medidaDePagina)
                                                    {
        return publicationService.list(numeroDePagina, medidaDePagina);
    }

    @GetMapping("/listarPublicaciones2")
    public PublicacionRespuesta listarPublicaciones2(@RequestParam(value = "nro", defaultValue = NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
                                                     @RequestParam(value = "pageSize",defaultValue = MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
                                                     @RequestParam(value = "sortBy",defaultValue = ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
                                                     @RequestParam(value = "sorDir",defaultValue = ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir){
        return publicationService.listar(numeroDePagina, medidaDePagina, ordenarPor,sortDir);
    }

    @PostMapping("/crearPublicacion")
    public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO dto){
        PublicacionDTO salida = publicationService.save(dto);
        return new ResponseEntity<>(salida, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable("id") Long id){
          return ResponseEntity.ok(publicationService.searchById(id));
    }

    @PutMapping("/actualizarPublicacion/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO dto,@PathVariable("id")Long id){
        PublicacionDTO publicacionRespuesta = publicationService.update(dto,id);

        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String>eliminar(@PathVariable("id") Long id){
        publicationService.delete(id);
        return new ResponseEntity<>("PUBLICACIÓN ELIMINADA",HttpStatus.OK);
    }
}
