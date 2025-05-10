package com.example.demo.services;

import com.example.demo.dto.Internship.InternshipDtoResponse;
import com.example.demo.dto.Internship.InternshipDtoRequest;
import com.example.demo.entities.InternshipEntity;
import jakarta.persistence.EntityNotFoundException;
import com.example.demo.mapping.InternshipMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.InternshipRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipService {
    private final InternshipRepository internshipRepository;

    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    public InternshipDtoResponse create(InternshipDtoRequest internshipDto) {
        InternshipEntity internship = InternshipMapper.toEntity(internshipDto);
        InternshipEntity savedInternship = internshipRepository.save(internship);
        return InternshipMapper.toDtoResponse(savedInternship);
    }

    public InternshipDtoResponse findById(Long id) {
        return internshipRepository.findById(id)
                .map(InternshipMapper::toDtoResponse)
                .orElseThrow(() -> new EntityNotFoundException("Стажировка не найдена ID: " + id));
    }

    public List<InternshipDtoResponse> findAll() {
        return internshipRepository.findAll().stream()
                .map(InternshipMapper::toDtoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public InternshipDtoResponse update(InternshipDtoRequest internshipDto, Long id) {
        InternshipEntity existingInternship = internshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Стажировка не найдена ID: " + id));

        existingInternship.setTitle(internshipDto.getTitle());
        existingInternship.setDescription(internshipDto.getDescription());
        existingInternship.setStatus(internshipDto.getStatus());
        existingInternship.setStartDate(internshipDto.getStartDate());
        existingInternship.setEndDate(internshipDto.getEndDate());

        // Обновляем только ID компании
        existingInternship.getCompany().setId(internshipDto.getCompanyId());

        InternshipEntity updatedInternship = internshipRepository.save(existingInternship);
        return InternshipMapper.toDtoResponse(updatedInternship);
    }

    public void deleteById(Long id) {
        internshipRepository.deleteById(id);
    }
}