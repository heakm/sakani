package com.hadef.sakani.domain.entity;

import com.hadef.sakani.domain.value.ProjectStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Project extends Auditable implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title_ar;
    private String title_en;
    
    @Column(unique = true)
    private String slugTitle;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_user_id")
    private User user;
//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_classification_id")
    private ProjectClassification classification;

//    @JsonManagedReference(value = "projectFaqReference")
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "projects",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ProjectFaq> projectFaq = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private String resourceUrl;
    private String mapUrl;
    private String videoUrl;
    private String mainImageUrl;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "image_list_url",
            joinColumns = @JoinColumn(name = "image_list_url_id")
    )
    @OrderColumn
    private List<String> imagesUrl = new ArrayList<>();

    @Lob
    private String description_ar;

    @Lob
    private String description_en;

    @Embedded
    private ProjectAddress projectAddress;

    @Embedded
    private ProjectDetails projectDetails;

    @Embedded
    private ProjectInternal projectInternal;

    @Embedded
    private ProjectExternal projectExternal;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Rating rating;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "project_reservation",
            joinColumns = @JoinColumn(name = "project_null"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Project() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(title_ar, project.title_ar) &&
                Objects.equals(title_en, project.title_en) &&
                Objects.equals(slugTitle, project.slugTitle) &&
                Objects.equals(projectType, project.projectType) &&
                Objects.equals(classification, project.classification) &&
                Objects.equals(projectStatus, project.projectStatus) &&
                Objects.equals(resourceUrl, project.resourceUrl) &&
                Objects.equals(mapUrl, project.mapUrl) &&
                Objects.equals(videoUrl, project.videoUrl) &&
                Objects.equals(mainImageUrl, project.mainImageUrl) &&
                Objects.equals(imagesUrl, project.imagesUrl) &&
                Objects.equals(description_ar, project.description_ar) &&
                Objects.equals(description_en, project.description_en) &&
                Objects.equals(projectAddress, project.projectAddress) &&
                Objects.equals(projectDetails, project.projectDetails) &&
                Objects.equals(projectInternal, project.projectInternal) &&
                Objects.equals(projectExternal, project.projectExternal) &&
                Objects.equals(rating, project.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title_ar, title_en, slugTitle, projectType, classification, projectStatus,
                resourceUrl, mapUrl, videoUrl, mainImageUrl, imagesUrl, description_ar, description_en,
                projectAddress, projectDetails, projectInternal, projectExternal, rating);
    }

    public Long getId() {
        return id;
    }

    public Project setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle_ar() {
        return title_ar;
    }

    public Project setTitle_ar(String title_ar) {
        this.title_ar = title_ar;
        return this;
    }

    public String getTitle_en() {
        return title_en;
    }

    public Project setTitle_en(String title_en) {
        this.title_en = title_en;
        return this;
    }

    public String getSlugTitle() {
        return slugTitle;
    }

    public Project setSlugTitle(String slugTitle) {
        this.slugTitle = slugTitle;
        return this;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public Project setProjectType(ProjectType projectType) {
        this.projectType = projectType;
        return this;
    }

    public ProjectClassification getClassification() {
        return classification;
    }

    public Project setClassification(ProjectClassification classification) {
        this.classification = classification;
        return this;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public Project setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public List<ProjectFaq> getProjectFaq() {
        return projectFaq;
    }

    public Project setProjectFaq(List<ProjectFaq> projectFaq) {
        this.projectFaq = projectFaq;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public Project setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public Project setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Project setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public Project setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
        return this;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public Project setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
        return this;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public Project setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public Project setDescription_en(String description_en) {
        this.description_en = description_en;
        return this;
    }

    public ProjectAddress getProjectAddress() {
        return projectAddress;
    }

    public Project setProjectAddress(ProjectAddress projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public Project setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
        return this;
    }

    public ProjectInternal getProjectInternal() {
        return projectInternal;
    }

    public Project setProjectInternal(ProjectInternal projectInternal) {
        this.projectInternal = projectInternal;
        return this;
    }

    public ProjectExternal getProjectExternal() {
        return projectExternal;
    }

    public Project setProjectExternal(ProjectExternal projectExternal) {
        this.projectExternal = projectExternal;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public Project setRating(Rating rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title_ar='" + title_ar + '\'' +
                ", title_en='" + title_en + '\'' +
                ", slugTitle='" + slugTitle + '\'' +
                ", projectType=" + projectType +
                ", classification=" + classification +
                ", projectStatus=" + projectStatus +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", mapUrl='" + mapUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", mainImageUrl='" + mainImageUrl + '\'' +
                ", imagesUrl=" + imagesUrl +
                ", description_ar='" + description_ar + '\'' +
                ", description_en='" + description_en + '\'' +
                ", projectAddress=" + projectAddress +
                ", projectDetails=" + projectDetails +
                ", projectInternal=" + projectInternal +
                ", projectExternal=" + projectExternal +
                ", rating=" + rating +
                '}';
    }

    public void addProjectFaq(ProjectFaq faq){
        this.projectFaq.add(faq);
        faq.setProjects(this);
    }

    public void deleteProjectFaq(ProjectFaq faq){
        faq.setProjects(null);
        this.projectFaq.remove(faq);
    }

    public void removeAllProjectFaq(){
        Iterator<ProjectFaq> projectFaqIterator = this.projectFaq.iterator();
        while(projectFaqIterator.hasNext()){
            ProjectFaq faqs = projectFaqIterator.next();
            faqs.setProjects(null);
            projectFaqIterator.remove();
        }
    }
}
