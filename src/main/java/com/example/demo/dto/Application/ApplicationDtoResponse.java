package com.example.demo.dto.Application;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationDtoResponse {
    private Long id;
    private String status;
    private LocalDateTime appliedAt;
    private Long studentId;
    private Long internshipId;
}