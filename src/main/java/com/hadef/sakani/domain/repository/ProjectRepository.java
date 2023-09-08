package com.hadef.sakani.domain.repository;

import com.hadef.sakani.domain.entity.Project;
import com.hadef.sakani.domain.entity.ProjectClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    boolean existsBySlugTitle(String slugTitle);
    Optional<Project> findBySlugTitle(String slugTitle);

    @Query("select p from Project p " +
            "where ?1 is null or p.title_en like %?1% " +
            "or p.title_ar like %?1%  ")
    Page<Project> search(String searchText, Pageable pageable);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Project a WHERE a.id = ?1")
    public int deleteByIdentifier(Long id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Project b WHERE b.classification IN ?1")
    public int deleteBulkByAuthors(List<ProjectClassification> classifications);
}
