package com.example.demo.mapping;


import com.example.demo.dto.Internship.InternshipDtoRequest;
import com.example.demo.dto.Internship.InternshipDtoResponse;
import com.example.demo.entities.CompanyEntity;
import com.example.demo.entities.InternshipEntity;

public class InternshipMapper {
    public static InternshipDtoResponse toDtoResponse(InternshipEntity internship) {
        InternshipDtoResponse dto = new InternshipDtoResponse();
        dto.setId(internship.getId());
        dto.setTitle(internship.getTitle());
        dto.setDescription(internship.getDescription());
        dto.setStatus(internship.getStatus().getDisplayName());
        dto.setStartDate(internship.getStartDate());
        dto.setEndDate(internship.getEndDate());
        dto.setCompanyId(internship.getCompany().getId());
        return dto;
    }

    public static InternshipEntity toEntity(InternshipDtoRequest dto) {
        InternshipEntity internship = new InternshipEntity();
        internship.setTitle(dto.getTitle());
        internship.setDescription(dto.getDescription());
        internship.setStatus(dto.getStatus());
        internship.setStartDate(dto.getStartDate());
        internship.setEndDate(dto.getEndDate());

        // Создаем временную компанию только с ID
        CompanyEntity company = new CompanyEntity();
        company.setId(dto.getCompanyId());
        internship.setCompany(company);

        return internship;
    }
}