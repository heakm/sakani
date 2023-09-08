package com.hadef.sakani.domain.service.impl;


import com.hadef.sakani.domain.entity.ProjectFaq;
import com.hadef.sakani.domain.repository.ProjectFaqRepository;
import com.hadef.sakani.domain.service.ProjectFaqService;
import com.hadef.sakani.domain.value.dto.ProjectFaqDTO;
import com.hadef.sakani.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hadef.sakani.util.LoggingUtil.logError;

@Service
public class ProjectFaqServiceImpl implements ProjectFaqService {

    private final ProjectFaqRepository projectFaqRepository;
    private final ModelMapper modelMapper;
    private final String serviceName;

    public ProjectFaqServiceImpl(ProjectFaqRepository projectFaqRepository, ModelMapper modelMapper) {
        this.projectFaqRepository = projectFaqRepository;
        this.modelMapper = modelMapper;
        this.serviceName = this.getClass().getName();
    }

    @Override
    public void addProjectFaq(List<ProjectFaqDTO> projectFaqDTOs) {
        List<ProjectFaq> collect = projectFaqDTOs.stream().map(
                e -> modelMapper.map(e, ProjectFaq.class)
        ).collect(Collectors.toList());
        projectFaqRepository.saveAll(collect);
    }

    @Override
    public List<ProjectFaq> findAllProjectFaq() {
        return null;
    }

    @Override
    public List<ProjectFaqDTO> getAllProjectFaq() {
        return projectFaqRepository.findAll()
                .stream()
                .map(faq -> modelMapper.map(faq,ProjectFaqDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeProjectFaq(Long id) {
        logError(serviceName,"Deleting project with id %s ",id);
        Optional<ProjectFaq> byId = projectFaqRepository.findById(id);
        if(byId.isPresent()){
            projectFaqRepository.delete(byId.get());
        }
        throw new CustomException("Deleting project with id");
    }
}
