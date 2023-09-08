package com.hadef.sakani.domain.repository;

import com.hadef.sakani.domain.entity.ProjectClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectClassificationRepository extends
        JpaRepository<ProjectClassification,Long>{

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM ProjectClassification a WHERE a.id = ?1")
    public int deleteByIdentifier(Long id);

}
