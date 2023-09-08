package com.hadef.sakani.domain.value.dto;


import java.util.List;

public class ProjectTypeDTO {
    private Long id;
    private String name;
    private List<Long> projectsId;

    public ProjectTypeDTO() {
    }

    public ProjectTypeDTO(Long id, String name, List<Long> projectsId) {
        this.id = id;
        this.name = name;
        this.projectsId = projectsId;
    }

    public Long getId() {
        return id;
    }

    public ProjectTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Long> getProjectsId() {
        return projectsId;
    }

    public ProjectTypeDTO setProjectsId(List<Long> projectsId) {
        this.projectsId = projectsId;
        return this;
    }

    @Override
    public String toString() {
        return "ProjectTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectsId=" + projectsId +
                '}';
    }
}
