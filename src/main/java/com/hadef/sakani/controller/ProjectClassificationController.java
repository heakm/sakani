package com.hadef.sakani.controller;

import com.hadef.sakani.domain.service.ProjectClassificationService;
import com.hadef.sakani.domain.value.FailureEnum;
import com.hadef.sakani.domain.value.dto.ProjectClassificationDTO;
import com.hadef.sakani.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/v1/project-classification")
public class ProjectClassificationController {

    private final ProjectClassificationService projectClassificationService;
    private final String serviceName;

    public ProjectClassificationController(ProjectClassificationService projectClassificationService) {
        this.projectClassificationService = projectClassificationService;
        this.serviceName = this.getClass().getName();
    }

    @PostMapping
    public ResponseEntity addNewProjectClassification(
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestBody ProjectClassificationDTO dto){
        projectClassificationService.addNewProjectClassifications(dto);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeProjectClassification(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @PathVariable Long id
    ){
        projectClassificationService.removeProjectClassifications(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectClassificationDTO>> getAllProjectClassifications(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang)
    {
        List<ProjectClassificationDTO> allProjectsClassification = projectClassificationService.getAllProjectsClassification();
        return ResponseEntity.ok(allProjectsClassification);
    }

    private void validateBindingResult(BindingResult bindingResult, FailureEnum failureEnum){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","))
                    ,failureEnum,serviceName);
        }
    }
}
