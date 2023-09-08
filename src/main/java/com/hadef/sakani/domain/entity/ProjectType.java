package com.hadef.sakani.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "project_type")
@NamedEntityGraph(name = "")
public class ProjectType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY ,
            cascade = CascadeType.ALL,
            mappedBy = "projectType",
            orphanRemoval = true)
    @JsonManagedReference(value = "projectTypeReference")
    private List<Project> projects = new ArrayList<>();
    public ProjectType() {

    }
    public ProjectType(Long id, String name, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
        project.setProjectType(this);
    }
    public void removeProject(Project project){
        project.setProjectType(null);
        this.projects.remove(project);
    }
    public void removeAllProjects(){
        Iterator<Project> iterator = this.projects.iterator();
        while (iterator.hasNext()) {
            Project project = iterator.next();
            project.setProjectType(null);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectType that = (ProjectType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projects);
    }

    public Long getId() {
        return id;
    }

    public ProjectType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectType setName(String name) {
        this.name = name;
        return this;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "ProjectType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projects=" + projects +
                '}';
    }
}