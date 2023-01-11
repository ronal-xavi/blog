package com.ronal.blog.service.impl;

import com.ronal.blog.dto.PublicacionDTO;
import com.ronal.blog.dto.PublicacionRespuesta;
import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.excepciones.ResourceNotFountException;
import com.ronal.blog.repository.PublicationRepository;
import com.ronal.blog.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicacionDTO save(PublicacionDTO dto) {
        //CONVERTIMOS DE DTO A ENTIDAD
        PublicationDAO publicationDAO = mapearEntity(dto);

        //REGISTRAMOS LA PUBLICACION
        PublicationDAO publicationDAONueva = publicationRepository.save(publicationDAO);

        //CONVERTIMOS DE ENTIDAD A DTO
        PublicacionDTO publicacionRespuesta = mapearDTO(publicationDAONueva);

        return publicacionRespuesta;
    }

    @Override
    public List<PublicacionDTO> list(int numeroDePagina, int medidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina);

        Page<PublicationDAO> publicacions = publicationRepository.findAll(pageable);

        List<PublicationDAO> listaDePublicaciones = publicacions.getContent();
       return listaDePublicaciones.stream().map(publicationDAO -> mapearDTO(publicationDAO)).collect(Collectors.toList());
    }

    @Override
    public PublicacionRespuesta listar(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina,sort);

        Page<PublicationDAO> publicacions = publicationRepository.findAll(pageable);

        List<PublicationDAO> listaDePublicaciones = publicacions.getContent();

        List<PublicacionDTO> contenido =  listaDePublicaciones.stream().map(publicationDAO -> mapearDTO(publicationDAO)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroDePagina(publicacions.getNumber());
        publicacionRespuesta.setMedidaDePagina(publicacions.getSize());
        publicacionRespuesta.setTotalElementos(publicacions.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicacions.getTotalPages());
        publicacionRespuesta.setUltima(publicacions.isLast());

        return publicacionRespuesta;
    }

    @Override
    public PublicacionDTO searchById(Long id) {
        PublicationDAO publicationDAO = publicationRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Publicacion", "id", id));
        return mapearDTO(publicationDAO);
    }

    @Override
    public PublicacionDTO update(PublicacionDTO dto, Long id) {

        PublicationDAO publicationDAO = publicationRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Publicación","id",id));
        publicationDAO.setTitle(dto.getTitle());
        publicationDAO.setDescription(dto.getDescription());
        publicationDAO.setContent(dto.getContent());

        PublicationDAO publicationDAOActualizada = publicationRepository.save(publicationDAO);

        return mapearDTO(publicationDAOActualizada);
    }

    @Override
    public void delete(Long id) {
        PublicationDAO publicationDAO = publicationRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Publicación","id",id));
        publicationRepository.delete(publicationDAO);

    }

    //ENTITY => DTO
    public PublicacionDTO mapearDTO(PublicationDAO publicationDAO){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setIdPublication(publicationDAO.getIdPublication());
        publicacionDTO.setTitle(publicationDAO.getTitle());
        publicacionDTO.setDescription(publicationDAO.getDescription());
        publicacionDTO.setContent(publicationDAO.getContent());
        return publicacionDTO;
    }
    public PublicationDAO mapearEntity(PublicacionDTO dto) {
        PublicationDAO publicationDAO = new PublicationDAO();
        publicationDAO.setTitle(dto.getTitle());
        publicationDAO.setDescription(dto.getDescription());
        publicationDAO.setContent(dto.getContent());
        return publicationDAO;
    }




























}