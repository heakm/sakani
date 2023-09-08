package com.hadef.sakani.domain.value.dto;

import com.hadef.sakani.domain.entity.*;
import com.hadef.sakani.domain.value.ProjectStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


public class ProjectDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title_ar;
    private String title_en;
    private String slugTitle;
    private Long projectTypeId;
    private Long classificationId;
    private ProjectStatus projectStatus;
    private ProjectFaq projectFaq;
    private String resourceUrl;
    private String mapUrl;
    private String videoUrl;
    private String mainImageUrl;
    private List<String> imagesUrl;
    private String description_ar;
    private String description_en;
    private ProjectAddress projectAddress;
    private ProjectDetails projectDetails;
    private ProjectInternal projectInternal;
    private ProjectExternal projectExternal;
    private Rating rating;

    public Long getId() {
        return id;
    }

    public ProjectDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle_ar() {
        return title_ar;
    }

    public ProjectDTO setTitle_ar(String title_ar) {
        this.title_ar = title_ar;
        return this;
    }

    public String getTitle_en() {
        return title_en;
    }

    public ProjectDTO setTitle_en(String title_en) {
        this.title_en = title_en;
        return this;
    }

    public String getSlugTitle() {
        return slugTitle;
    }

    public ProjectDTO setSlugTitle(String slugTitle) {
        this.slugTitle = slugTitle;
        return this;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public ProjectDTO setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
        return this;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public ProjectDTO setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
        return this;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public ProjectDTO setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public ProjectFaq getProjectFaq() {
        return projectFaq;
    }

    public ProjectDTO setProjectFaq(ProjectFaq projectFaq) {
        this.projectFaq = projectFaq;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public ProjectDTO setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public ProjectDTO setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ProjectDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public ProjectDTO setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
        return this;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public ProjectDTO setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
        return this;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public ProjectDTO setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public ProjectDTO setDescription_en(String description_en) {
        this.description_en = description_en;
        return this;
    }

    public ProjectAddress getProjectAddress() {
        return projectAddress;
    }

    public ProjectDTO setProjectAddress(ProjectAddress projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public ProjectDTO setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
        return this;
    }

    public ProjectInternal getProjectInternal() {
        return projectInternal;
    }

    public ProjectDTO setProjectInternal(ProjectInternal projectInternal) {
        this.projectInternal = projectInternal;
        return this;
    }

    public ProjectExternal getProjectExternal() {
        return projectExternal;
    }

    public ProjectDTO setProjectExternal(ProjectExternal projectExternal) {
        this.projectExternal = projectExternal;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public ProjectDTO setRating(Rating rating) {
        this.rating = rating;
        return this;
    }

}
