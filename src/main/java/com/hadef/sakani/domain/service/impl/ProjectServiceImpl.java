package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.Project;
import com.hadef.sakani.domain.entity.ProjectClassification;
import com.hadef.sakani.domain.entity.ProjectFaq;
import com.hadef.sakani.domain.entity.ProjectType;
import com.hadef.sakani.domain.repository.ProjectClassificationRepository;
import com.hadef.sakani.domain.repository.ProjectRepository;
import com.hadef.sakani.domain.repository.ProjectTypeRepository;
import com.hadef.sakani.domain.service.ProjectService;
import com.hadef.sakani.domain.value.dto.AddingProjectFAQDTO;
import com.hadef.sakani.domain.value.dto.ProjectDTO;
import com.hadef.sakani.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hadef.sakani.util.LoggingUtil.logError;
import static com.hadef.sakani.util.LoggingUtil.logInfo;


@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectClassificationRepository projectClassificationRepository;
    private final String serviceName;

    public ProjectServiceImpl(ModelMapper modelMapper,
                              ProjectRepository projectRepository,
                              ProjectTypeRepository projectTypeRepository,
                              ProjectClassificationRepository projectClassificationRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.projectTypeRepository = projectTypeRepository;
        this.projectClassificationRepository = projectClassificationRepository;
        this.serviceName = this.getClass().getName();
    }

    @Override
    public Page<Project> getAllProjects(Pageable pageable) {

        return null;
    }

    @Override
    public ProjectDTO addNewProject(ProjectDTO dto) {
        logInfo(serviceName,"adding new project with %s",dto.getSlugTitle());
        List<String> errors = new ArrayList<String>();
        final Optional<ProjectType> projectTypeById = projectTypeRepository.findById(dto.getProjectTypeId());
        if(!projectTypeById.isPresent()) {
            logError(serviceName,"Project Type is not available");
            errors.add("Project Type is not available");
        }
        final Optional<ProjectClassification> projectClassificationById = projectClassificationRepository.findById(dto.getProjectTypeId());
        if(!projectTypeById.isPresent()) {
            logError(serviceName,"Project classification is not available");
            errors.add("Project classification is not available");
        }
        if(!errors.isEmpty()){
            throw new CustomException(errors.toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    serviceName);
        }
        Project project = new Project()
                .setProjectExternal(dto.getProjectExternal())
                .setImagesUrl(dto.getImagesUrl())
                .setClassification(projectClassificationById.get())
                .setDescription_ar(dto.getDescription_ar())
                .setProjectDetails(dto.getProjectDetails())
                .setDescription_en(dto.getDescription_en())
                .setProjectAddress(dto.getProjectAddress())
                .setProjectInternal(dto.getProjectInternal())
                .setProjectStatus(dto.getProjectStatus())
                .setProjectType(projectTypeById.get())
                .setMainImageUrl(dto.getMainImageUrl())
                .setMapUrl(dto.getMapUrl())
                .setRating(dto.getRating())
                .setSlugTitle(dto.getSlugTitle())
                .setTitle_ar(dto.getTitle_ar())
                .setTitle_en(dto.getTitle_en())
                .setResourceUrl(dto.getResourceUrl())
                .setVideoUrl(dto.getVideoUrl());
        projectRepository.save(project);
        return dto;
    }

    @Override
    public void addProjectFAQ(AddingProjectFAQDTO dtos) {
        Optional<Project> byId = projectRepository.findById(dtos.getProjectId());

        if(byId.isPresent()){
            List<ProjectFaq> collect = dtos.getFaqs().stream()
                    .map(e -> modelMapper.map(e, ProjectFaq.class))
                    .collect(Collectors.toList());
            Project project = byId.get().setProjectFaq(collect);
            projectRepository.save(project);
        }
        throw new CustomException("Project not found");
    }
}
