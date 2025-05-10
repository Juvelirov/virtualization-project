package com.example.demo.mapping;

import com.example.demo.dto.Student.UserDtoRequest;
import com.example.demo.dto.Student.UserDtoResponse;
import com.example.demo.entities.StudentEntity;

public class StudentMapper {
    public static UserDtoResponse toDtoResponse(StudentEntity student) {
        UserDtoResponse dto = new UserDtoResponse();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        return dto;
    }

    public static StudentEntity toEntity(UserDtoRequest dto) {
        StudentEntity student = new StudentEntity();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        return student;
    }
}
