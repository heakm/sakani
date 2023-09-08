package com.hadef.sakani.domain.service;

import com.hadef.sakani.domain.entity.ProjectClassification;
import com.hadef.sakani.domain.entity.ProjectFaq;
import com.hadef.sakani.domain.value.dto.ProjectClassificationDTO;
import com.hadef.sakani.domain.value.dto.ProjectFaqDTO;

import java.util.List;

public interface ProjectFaqService {

    void addProjectFaq(List<ProjectFaqDTO> projectFaqDTOs);
    List<ProjectFaq> findAllProjectFaq();
    List<ProjectFaqDTO> getAllProjectFaq();

    void removeProjectFaq(Long id);
}
