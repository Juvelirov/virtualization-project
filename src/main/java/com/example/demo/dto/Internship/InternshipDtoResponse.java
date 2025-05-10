package com.example.demo.dto.Internship;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InternshipDtoResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long companyId;
}