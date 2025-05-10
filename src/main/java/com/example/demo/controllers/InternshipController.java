package com.example.demo.controllers;

import com.example.demo.dto.Internship.InternshipDtoRequest;
import com.example.demo.dto.Internship.InternshipDtoResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.InternshipService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internships")
public class InternshipController {
    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping
    public InternshipDtoResponse create(@Valid @RequestBody InternshipDtoRequest internship) {
        return internshipService.create(internship);
    }

    @GetMapping
    public List<InternshipDtoResponse> getAll() {
        return internshipService.findAll();
    }

    @GetMapping("/{id}")
    public InternshipDtoResponse getById(@PathVariable Long id) {
        return internshipService.findById(id);
    }

    @PutMapping("/{id}")
    public InternshipDtoResponse update(@Valid @RequestBody InternshipDtoRequest internship,
                                        @PathVariable Long id) {
        return internshipService.update(internship, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        internshipService.deleteById(id);
    }
}