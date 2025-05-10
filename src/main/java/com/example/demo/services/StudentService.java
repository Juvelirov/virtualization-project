package com.example.demo.services;

import com.example.demo.dto.Student.UserDtoRequest;
import com.example.demo.dto.Student.UserDtoResponse;
import com.example.demo.entities.StudentEntity;
import com.example.demo.mapping.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Создать студента из DTO
    public UserDtoResponse create(UserDtoRequest dto) {
        StudentEntity student = StudentMapper.toEntity(dto);
        student = studentRepository.save(student);
        return StudentMapper.toDtoResponse(student);
    }

    // Получить студента по id
    public Optional<UserDtoResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDtoResponse);
    }

    // Получить всех студентов
    public List<UserDtoResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDtoResponse)
                .toList();
    }

    // Обновить студента
    @Transactional
    public UserDtoResponse update(UserDtoRequest dto, Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student = studentRepository.save(student);
        return StudentMapper.toDtoResponse(student);
    }

    // Удалить студента по id
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
