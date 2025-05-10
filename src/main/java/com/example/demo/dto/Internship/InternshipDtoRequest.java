package com.example.demo.dto.Internship;

import com.example.demo.enums.InternshipStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InternshipDtoRequest {
    @NotBlank(message = "Название стажировки обязательно")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    private String title;

    @Size(max = 2000, message = "Описание не должно превышать 2000 символов")
    private String description;

    @NotNull(message = "Статус стажировки обязателен")
    private InternshipStatus status;

    @FutureOrPresent(message = "Дата начала должна быть в будущем или сегодня")
    private LocalDate startDate;

    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDate endDate;

    @NotNull(message = "ID компании обязательно")
    private Long companyId;
}