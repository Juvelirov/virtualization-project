package com.example.demo.dto.Company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyDtoRequest {
    @NotBlank(message = "Название компании не может быть пустым!")
    @Size(max = 40, message = "Название компании не должно превышать 40 символов!")
    private String name;

    @Size(max = 100, message = "Описание компании не должно превышать 100 символов!")
    private String description;

    @NotBlank(message = "Email компании не может быть пустым!")
    @Email(message = "Некорректный формат email!")
    @Size(max = 40, message = "Email не должен превышать 40 символов!")
    private String contactEmail;

    @NotBlank(message = "Телефон компании не может быть пустым!")
    @Pattern(regexp = "^\\+?[0-9\\s()-]{10,20}$",
            message = "Некорректный формат телефона!")
    private String contactPhone;
}