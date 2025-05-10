package com.example.demo.dto.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Для студента
@Data
public class UserDtoRequest {
    @NotBlank(message = "Имя обязательно к заполнению!")
    @Size(max = 30, message = "Имя не должно быть длиннее 30 символов!")
    private String name;

    @NotBlank(message = "Email обязателен к заполнению!")
    @Email(message = "Email должен быть корректным!")
    private String email;

    private String phone;
}
