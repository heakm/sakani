package com.hadef.sakani.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class ProjectClassification  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonManagedReference(value = "projectClassificationReference")
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "classification",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @Override
    public String toString() {
        return "ProjectClassification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projects=" + projects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectClassification that = (ProjectClassification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projects);
    }

    public Long getId() {
        return id;
    }

    public ProjectClassification setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public ProjectClassification setProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectClassification setName(String name) {
        this.name = name;
        return this;
    }

    public void addProject(Project project){
        this.projects.add(project);
        project.setClassification(this);
    }
    public void remove(Project project){
        project.setClassification(null);
        this.projects.remove(project);
    }
    public void removeAllProject(){
        Iterator<Project> iterator = this.projects.iterator();
        while (iterator.hasNext()) {
            Project project = iterator.next();
            project.setClassification(null);
            iterator.remove();
        }
    }
}
