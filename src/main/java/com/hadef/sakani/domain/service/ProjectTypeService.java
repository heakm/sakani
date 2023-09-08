package com.hadef.sakani.domain.service;

import com.hadef.sakani.domain.entity.ProjectType;
import com.hadef.sakani.domain.value.dto.ProjectTypeDTO;

import java.util.List;

public interface ProjectTypeService {

    ProjectType addNewProjectType(ProjectTypeDTO projectType);
    ProjectType deleteProjectType(Long id);

    List<ProjectTypeDTO> getAllProjectType();
}
