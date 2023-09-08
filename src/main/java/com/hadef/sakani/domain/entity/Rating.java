package com.hadef.sakani.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "rating")
public class Rating  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long total;
    private Long count;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Rating() {
    }

    public Rating(Long id, Long total, Long count, Project project) {
        this.id = id;
        this.total = total;
        this.count = count;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id) && Objects.equals(total, rating.total) && Objects.equals(count, rating.count) && Objects.equals(project, rating.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, count, project);
    }

    public Long getId() {
        return id;
    }

    public Rating setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public Rating setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public Rating setCount(Long count) {
        this.count = count;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Rating setProject(Project project) {
        this.project = project;
        return this;
    }
}