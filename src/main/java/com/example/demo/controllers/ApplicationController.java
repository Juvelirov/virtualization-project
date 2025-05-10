package com.example.demo.controllers;

import com.example.demo.enums.ApplicationStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.ApplicationService;
import java.util.List;
import com.example.demo.dto.Application.ApplicationDtoResponse;
import com.example.demo.dto.Application.ApplicationDtoRequest;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationDtoResponse create(@Valid @RequestBody ApplicationDtoRequest application) {
        return applicationService.create(application);
    }

    @GetMapping
    public List<ApplicationDtoResponse> getAll() {
        return applicationService.findAll();
    }

    @GetMapping("/{id}")
    public ApplicationDtoResponse getById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @PutMapping("/{id}")
    public ApplicationDtoResponse update(@Valid @RequestBody ApplicationDtoRequest application,
                                         @PathVariable Long id) {
        return applicationService.update(application, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationService.deleteById(id);
    }

    @PatchMapping("/{id}/status")
    public ApplicationDtoResponse changeStatus(
            @PathVariable Long id,
            @RequestParam Long companyId,
            @RequestParam ApplicationStatus status) {

        return applicationService.changeApplicationStatus(id, companyId, status);
    }
}