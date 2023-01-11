package com.ronal.blog.service.impl;

import com.ronal.blog.dto.PublicationDTO;
import com.ronal.blog.dto.PublicationResponse;
import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.dto.ResponseDTO;
import com.ronal.blog.mapper.PublicationMapper;
import com.ronal.blog.repository.PublicationRepository;
import com.ronal.blog.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;


    @Override
    public ResponseDTO<PublicationDTO> save(PublicationDTO dto) {

        PublicationDAO publicationDAO = publicationMapper.publicationDtoToPublicationDao(dto);

        publicationRepository.save(publicationDAO);

        PublicationDTO salida  = publicationMapper.publicationDaoToPublicationDto(publicationDAO);

        return ResponseDTO.<PublicationDTO>builder()
                .success(Boolean.TRUE)
                .mensaje("REGISTRADO CORRECTAMENTE")
                .response(salida).build();
    }

    @Override
    public List<PublicationDTO> listaRonal() {
        List<PublicationDAO> listaDAO = publicationRepository.findAll();
        return  publicationMapper.lstToDto(listaDAO);
    }

    @Override
    public List<PublicationDTO> list(int numeroDePagina, int medidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina);

        Page<PublicationDAO> publicacions = publicationRepository.findAll(pageable);

        List<PublicationDAO> listaDePublicaciones = publicacions.getContent();
       return listaDePublicaciones.stream().map(publicationDAO -> mapearDTO(publicationDAO)).collect(Collectors.toList());
    }

    @Override
    public PublicationResponse listar(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina,sort);

        Page<PublicationDAO> publicacions = publicationRepository.findAll(pageable);

        List<PublicationDAO> listaDePublicaciones = publicacions.getContent();

        List<PublicationDTO> contenido =  listaDePublicaciones.stream().map(publicationDAO -> mapearDTO(publicationDAO)).collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContenido(contenido);
        publicationResponse.setNumeroDePagina(publicacions.getNumber());
        publicationResponse.setMedidaDePagina(publicacions.getSize());
        publicationResponse.setTotalElementos(publicacions.getTotalElements());
        publicationResponse.setTotalPaginas(publicacions.getTotalPages());
        publicationResponse.setUltima(publicacions.isLast());

        return publicationResponse;
    }

    @Override
    public PublicationDTO searchById(Long id) {
        PublicationDAO publicationDAO = publicationRepository.findById(id).orElse(null);
        return publicationMapper.publicationDaoToPublicationDto(publicationDAO);
    }

    @Override
    public PublicationDTO update(PublicationDTO dto, Long id) {

        PublicationDAO publicationDAO = publicationRepository.findById(id).orElse(null);
        if (Objects.isNull(publicationDAO)){
            return null;
        }

        publicationDAO.setTitle(dto.getTitle());
        publicationDAO.setDescription(dto.getDescription());
        publicationDAO.setContent(dto.getContent());

        publicationRepository.save(publicationDAO);

        return publicationMapper.publicationDaoToPublicationDto(publicationDAO);
    }

    @Override
    public void delete(Long id) {
        PublicationDAO publicationDAO = publicationRepository.findById(id).orElse(null);

        publicationRepository.delete(publicationDAO);
    }


    //ENTITY => DTO
    @Deprecated
    public PublicationDTO mapearDTO(PublicationDAO publicationDAO){
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setIdPublication(publicationDAO.getIdPublication());
        publicationDTO.setTitle(publicationDAO.getTitle());
        publicationDTO.setDescription(publicationDAO.getDescription());
        publicationDTO.setContent(publicationDAO.getContent());
        return publicationDTO;
    }
    @Deprecated
    public PublicationDAO mapearEntity(PublicationDTO dto) {
        PublicationDAO publicationDAO = new PublicationDAO();
        publicationDAO.setTitle(dto.getTitle());
        publicationDAO.setDescription(dto.getDescription());
        publicationDAO.setContent(dto.getContent());
        return publicationDAO;
    }




























}