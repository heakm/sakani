package com.hadef.sakani.domain.value.dto;

import com.hadef.sakani.domain.entity.Project;

import java.util.Objects;


public class ProjectFaqDTO {

    private Long id;
    private String question;
    private String answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectFaqDTO that = (ProjectFaqDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    public ProjectFaqDTO() {
    }

    public ProjectFaqDTO(Long id, String question, String answer, Project project) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer);
    }

    public Long getId() {
        return id;
    }

    public ProjectFaqDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public ProjectFaqDTO setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public ProjectFaqDTO setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

}
