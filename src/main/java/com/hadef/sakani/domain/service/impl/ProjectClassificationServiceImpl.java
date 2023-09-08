package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.ProjectClassification;
import com.hadef.sakani.domain.repository.ProjectClassificationRepository;
import com.hadef.sakani.domain.service.ProjectClassificationService;
import com.hadef.sakani.domain.value.dto.ProjectClassificationDTO;
import com.hadef.sakani.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class ProjectClassificationServiceImpl implements ProjectClassificationService {

    private final ProjectClassificationRepository projectClassificationRepository;
    private final ModelMapper modelMapper;

    public ProjectClassificationServiceImpl(ProjectClassificationRepository projectClassificationRepository,
                                            ModelMapper modelMapper) {
        this.projectClassificationRepository = projectClassificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProjectClassificationDTO> getAllProjectsClassification() {

        ProjectClassificationDTO projectClassificationDTO = new ProjectClassificationDTO();
        List<ProjectClassificationDTO> collect = projectClassificationRepository.findAll()
                .stream()
                .map(projectClassification -> projectClassificationDTO
                        .setName(projectClassification.getName())
                        .setId(projectClassification.getId())
                        .setProjectsId(projectClassification.getProjects().stream().map(h -> h.getId()).collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public void removeProjectClassifications(Long id) {
        Optional<ProjectClassification> byId = projectClassificationRepository.findById(id);
        if(byId.isPresent()){
            byId.get().removeAllProject();
            projectClassificationRepository.delete(byId.get());
        }
    }

    @Override
    public void addNewProjectClassifications(ProjectClassificationDTO dto) {
        ProjectClassification projectClassification = new ProjectClassification()
                .setName(dto.getName());
        projectClassificationRepository.save(projectClassification);
    }

    @Override
    public ProjectClassification getProjectClassification(Long id) {
        Optional<ProjectClassification> projectClassificationById = projectClassificationRepository.findById(id);
        if(projectClassificationById.isPresent()){
            return projectClassificationById.get();
        }
        throw new CustomException("project classification not found");
    }
}
