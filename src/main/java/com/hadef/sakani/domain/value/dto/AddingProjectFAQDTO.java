package com.hadef.sakani.domain.value.dto;

import java.util.List;

public class AddingProjectFAQDTO {

    private Long id;
    private List<ProjectFaqDTO> faqs;
    private Long projectId;

    public AddingProjectFAQDTO() {
    }

    public AddingProjectFAQDTO(Long id, List<ProjectFaqDTO> faqs, Long projectId) {
        this.id = id;
        this.faqs = faqs;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProjectFaqDTO> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<ProjectFaqDTO> faqs) {
        this.faqs = faqs;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
