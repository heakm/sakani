package com.hadef.sakani.domain.service;

import com.hadef.sakani.domain.entity.ProjectClassification;
import com.hadef.sakani.domain.value.dto.ProjectClassificationDTO;

import java.util.List;

public interface ProjectClassificationService {

    void addNewProjectClassifications(ProjectClassificationDTO projectClassificationDTO);
    ProjectClassification getProjectClassification(Long id);
    List<ProjectClassificationDTO> getAllProjectsClassification();

    void removeProjectClassifications(Long id);
}
