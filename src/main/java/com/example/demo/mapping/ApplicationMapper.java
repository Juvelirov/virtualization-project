package com.example.demo.mapping;

import com.example.demo.dto.Application.ApplicationDtoResponse;
import com.example.demo.dto.Application.ApplicationDtoRequest;
import com.example.demo.entities.ApplicationEntity;
import com.example.demo.entities.InternshipEntity;
import com.example.demo.entities.StudentEntity;

public class ApplicationMapper {
    public static ApplicationDtoResponse toDtoResponse(ApplicationEntity application) {
        ApplicationDtoResponse dto = new ApplicationDtoResponse();
        dto.setId(application.getId());
        dto.setStatus(application.getStatus().getDisplayName());
        dto.setAppliedAt(application.getAppliedAt());
        dto.setStudentId(application.getStudent().getId());
        dto.setInternshipId(application.getInternship().getId());
        return dto;
    }

    public static ApplicationEntity toEntity(ApplicationDtoRequest dto) {
        ApplicationEntity application = new ApplicationEntity();

        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());

        InternshipEntity internship = new InternshipEntity();
        internship.setId(dto.getInternshipId());

        application.setStudent(student);
        application.setInternship(internship);
        return application;
    }
}