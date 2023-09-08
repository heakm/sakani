package com.hadef.sakani.domain.repository;

import com.hadef.sakani.domain.entity.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectTypeRepository extends JpaRepository<ProjectType,Long>{
    List<ProjectType> findByNameContaining(String name);
}
