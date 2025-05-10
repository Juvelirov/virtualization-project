package com.example.demo.mapping;

import com.example.demo.dto.Company.CompanyDtoRequest;
import com.example.demo.dto.Company.CompanyDtoResponse;
import com.example.demo.entities.CompanyEntity;

public class CompanyMapper {
    public static CompanyDtoResponse toDtoResponse(CompanyEntity company) {
        CompanyDtoResponse dto = new CompanyDtoResponse();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setDescription(company.getDescription());
        dto.setContactEmail(company.getContactEmail());
        dto.setContactPhone(company.getContactPhone());
        return dto;
    }

    public static CompanyEntity toEntity(CompanyDtoRequest dto) {
        CompanyEntity company = new CompanyEntity();
        company.setName(dto.getName());
        company.setDescription(dto.getDescription());
        company.setContactEmail(dto.getContactEmail());
        company.setContactPhone(dto.getContactPhone());
        return company;
    }
}