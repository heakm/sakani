package com.hadef.sakani.controller;

import com.hadef.sakani.domain.service.ProjectTypeService;
import com.hadef.sakani.domain.value.dto.ProjectTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/project-type")
@CrossOrigin
public class ProjectTypeController {

    private final ProjectTypeService projectTypeService;

    public ProjectTypeController(ProjectTypeService projectTypeService) {
        this.projectTypeService = projectTypeService;
    }

    @PostMapping
    public ResponseEntity<String> addNewProjectType(@RequestBody ProjectTypeDTO projectType){
        projectTypeService.addNewProjectType(projectType);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectTypeDTO>> getAllProjectType(
            @RequestHeader(name = "CHN") @Valid String chn
    ){
        List<ProjectTypeDTO> allProjectType = projectTypeService.getAllProjectType();
        return ResponseEntity.ok(allProjectType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAllProjectType(@PathVariable("id") @Valid Long id){
        projectTypeService.deleteProjectType(id);
        return ResponseEntity.ok("success");
    }
}
