package com.ronal.blog.service.impl;

import com.ronal.blog.dao.PublicationDAO;
import com.ronal.blog.dto.PublicationDTO;
import com.ronal.blog.dto.PublicationResponseDTO;
import com.ronal.blog.dto.ResponseDTO;
import com.ronal.blog.exceptions.RequestException;
import com.ronal.blog.mapper.PublicationMapper;
import com.ronal.blog.repository.PublicationRepository;
import com.ronal.blog.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ronal.blog.util.Constantes.BAD_REQUEST_TITLE;

@RequiredArgsConstructor
@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;


    @Override
    public PublicationResponseDTO listPublications(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<PublicationDAO> publicationDAOS = publicationRepository.findAll(pageable);
        List<PublicationDAO> listaDePublicaciones = publicationDAOS.getContent();
        List<PublicationDTO> listaSalida = publicationMapper.listDaoToListDto(listaDePublicaciones);
        return PublicationResponseDTO.builder().contenido(listaSalida).pageNumber(publicationDAOS.getNumber()).pageSize(publicationDAOS.getSize()).totalElementos(publicationDAOS.getTotalElements()).totalPaginas(publicationDAOS.getTotalPages()).build();
    }

    @Override
    public ResponseDTO<PublicationDTO> save(PublicationDTO dto) {
        PublicationDAO publicationDAO = publicationMapper.publicationDtoToPublicationDao(dto);
        publicationRepository.save(publicationDAO);
        PublicationDTO salida = publicationMapper.publicationDaoToPublicationDto(publicationDAO);
        return ResponseDTO.<PublicationDTO>builder().success(Boolean.TRUE).mensaje("REGISTRADO CORRECTAMENTE").response(salida).build();
    }

    @Override
    public PublicationDTO searchById(Long id) {
        PublicationDAO publicationDAO = publicationRepository.findById(id).orElse(null);
        return publicationMapper.publicationDaoToPublicationDto(publicationDAO);
    }

    @Override
    public PublicationDTO update(PublicationDTO dto, Long id) {

        PublicationDAO publicationDAO = publicationRepository.findById(id).orElse(null);
        if (Objects.isNull(publicationDAO)) {
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
        if (Objects.isNull(publicationDAO)) {
            throw new RequestException(BAD_REQUEST_TITLE, "C-50", HttpStatus.BAD_REQUEST);
        }
        publicationRepository.delete(publicationDAO);
    }
}