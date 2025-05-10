package com.example.demo.dto.Application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationDtoRequest {
    @NotNull(message = "ID студента обязательно к заполнению!")
    private Long studentId;

    @NotNull(message = "ID заявки обязательно к заполнению!")
    private Long internshipId;
}