package com.example.demo.dto.Company;

import lombok.Data;

@Data
public class CompanyDtoResponse {
    private Long id;
    private String name;
    private String description;
    private String contactEmail;
    private String contactPhone;
}