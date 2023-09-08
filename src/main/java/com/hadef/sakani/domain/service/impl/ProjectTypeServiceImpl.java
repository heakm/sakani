package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.ProjectType;
import com.hadef.sakani.domain.repository.ProjectTypeRepository;
import com.hadef.sakani.domain.service.ProjectTypeService;
import com.hadef.sakani.domain.value.dto.ProjectClassificationDTO;
import com.hadef.sakani.domain.value.dto.ProjectTypeDTO;
import com.hadef.sakani.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    private final ModelMapper modelMapper;
    private final String serviceName;
    private final ProjectTypeRepository projectTypeRepository;

    public ProjectTypeServiceImpl(ModelMapper modelMapper, ProjectTypeRepository projectTypeRepository) {
        this.modelMapper = modelMapper;
        this.projectTypeRepository = projectTypeRepository;
        this.serviceName = this.getClass().getName();
    }

    @Override
    public ProjectType addNewProjectType(ProjectTypeDTO projectType) {
        ProjectType type = new ProjectType()
                .setName(projectType.getName());
        return projectTypeRepository.save(type);
    }

    @Override
    public ProjectType deleteProjectType(Long id) {
        Optional<ProjectType> byId = projectTypeRepository.findById(id);
        if(byId.isPresent()){
            projectTypeRepository.delete(byId.get());
        }
        throw new CustomException(
                "Project type with id %s not found",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                serviceName);
    }

    @Override
    public List<ProjectTypeDTO> getAllProjectType() {
        ProjectTypeDTO projectTypeDTO = new ProjectTypeDTO();
        List<ProjectTypeDTO> collect = projectTypeRepository.findAll()
                .stream()
                .map(projectType -> projectTypeDTO
                        .setName(projectType.getName())
                        .setId(projectType.getId())
                        .setProjectsId(projectType.getProjects().stream().map(h -> h.getId()).collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
        return collect;
    }
}
