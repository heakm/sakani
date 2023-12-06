package com.hadef.sakani.controller;

import com.hadef.sakani.domain.service.ProjectClassificationService;
import com.hadef.sakani.domain.service.ProjectService;
import com.hadef.sakani.domain.value.dto.AddingProjectFAQDTO;
import com.hadef.sakani.domain.value.dto.ProjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/project")
@Slf4j
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final String serviceName;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
        this.serviceName = this.getClass().getName();
    }

    //    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<ProjectDTO> addNewProject(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestBody @Valid ProjectDTO dto){
        ProjectDTO projectDTO = projectService.addNewProject(dto);
        return ResponseEntity.ok(projectDTO);
    }

    @PostMapping("/faq")
    @ResponseStatus(HttpStatus.CREATED)
    public void addingProjectFAQ(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestBody @Valid AddingProjectFAQDTO dtos
    ){
        projectService.addProjectFAQ(dtos);
    }
}
