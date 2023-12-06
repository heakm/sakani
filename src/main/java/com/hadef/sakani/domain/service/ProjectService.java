package com.hadef.sakani.domain.service;

import com.hadef.sakani.domain.entity.Project;
import com.hadef.sakani.domain.value.dto.AddingProjectFAQDTO;
import com.hadef.sakani.domain.value.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<Project> getAllProjects(Pageable pageable );
    ProjectDTO addNewProject(ProjectDTO project);

    void addProjectFAQ(AddingProjectFAQDTO dtos);
}
