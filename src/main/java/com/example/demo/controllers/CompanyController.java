package com.example.demo.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.services.CompanyService;
import com.example.demo.dto.Company.CompanyDtoRequest;
import com.example.demo.dto.Company.CompanyDtoResponse;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyDtoResponse create(@Valid @RequestBody CompanyDtoRequest company) {
        return companyService.create(company);
    }

    @GetMapping
    public List<CompanyDtoResponse> getAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDtoResponse getById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PutMapping("/{id}")
    public CompanyDtoResponse update(@Valid @RequestBody CompanyDtoRequest company,
                                     @PathVariable Long id) {
        return companyService.update(company, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}