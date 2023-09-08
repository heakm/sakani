package com.hadef.sakani.domain.value.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hadef.sakani.domain.entity.Project;

import java.util.List;

public class ProjectClassificationDTO {
    private Long id;
    private String name;
    private List<Long> projectsId;
    public ProjectClassificationDTO() {
    }

    public ProjectClassificationDTO(Long id, String name, List<Long> projectsId) {
        this.id = id;
        this.name = name;
        this.projectsId = projectsId;
    }

    public Long getId() {
        return id;
    }

    public ProjectClassificationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectClassificationDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Long> getProjectsId() {
        return projectsId;
    }

    public ProjectClassificationDTO setProjectsId(List<Long> projectsId) {
        this.projectsId = projectsId;
        return this;
    }

    @Override
    public String toString() {
        return "ProjectClassificationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectsId=" + projectsId +
                '}';
    }
}
