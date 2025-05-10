package com.example.demo.dto.Student;

import lombok.Data;

// Для студента
@Data
public class UserDtoResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
