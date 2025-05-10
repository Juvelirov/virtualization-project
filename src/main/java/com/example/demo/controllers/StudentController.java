package com.example.demo.controllers;

import com.example.demo.dto.Student.UserDtoRequest;
import com.example.demo.dto.Student.UserDtoResponse;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.StudentService;
import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Создать студента
    @PostMapping
    public UserDtoResponse create(@Valid @RequestBody UserDtoRequest dto) {
        return studentService.create(dto);
    }

    // Получить всех студентов
    @GetMapping
    public List<UserDtoResponse> getAll() {
        return studentService.findAll();
    }

    // Получить студента по id
    @GetMapping("/{id}")
    public Optional<UserDtoResponse> getById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    // Обновить студента
    @PutMapping("/{id}")
    public UserDtoResponse update(@Valid @RequestBody UserDtoRequest dto, @PathVariable Long id) {
        return studentService.update(dto, id);
    }

    // Удалить студента по id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
