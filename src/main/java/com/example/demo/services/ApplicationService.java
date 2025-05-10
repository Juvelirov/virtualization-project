package com.example.demo.services;

import com.example.demo.dto.Application.ApplicationDtoRequest;
import com.example.demo.dto.Application.ApplicationDtoResponse;
import com.example.demo.entities.ApplicationEntity;
import com.example.demo.entities.InternshipEntity;
import com.example.demo.enums.ApplicationStatus;
import com.example.demo.exceptions.AccessDeniedException;
import com.example.demo.repositories.InternshipRepository;
import jakarta.persistence.EntityNotFoundException;
import com.example.demo.mapping.ApplicationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.ApplicationRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final InternshipRepository internshipRepository;

    public ApplicationService(ApplicationRepository applicationRepository, InternshipRepository internshipRepository) {
        this.applicationRepository = applicationRepository;
        this.internshipRepository = internshipRepository;
    }

    public ApplicationDtoResponse create(ApplicationDtoRequest applicationDto) {
        ApplicationEntity application = ApplicationMapper.toEntity(applicationDto);
        application.setStatus(ApplicationStatus.SENT);
        ApplicationEntity savedApplication = applicationRepository.save(application);
        return ApplicationMapper.toDtoResponse(savedApplication);
    }

    public ApplicationDtoResponse findById(Long id) {
        return applicationRepository.findById(id)
                .map(ApplicationMapper::toDtoResponse)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с указанным ID не найдена: " + id));
    }

    public List<ApplicationDtoResponse> findAll() {
        return applicationRepository.findAll().stream()
                .map(ApplicationMapper::toDtoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ApplicationDtoResponse update(ApplicationDtoRequest applicationDto, Long id) {
        ApplicationEntity existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с указанным ID не найдена: " + id));

        ApplicationEntity updatedData = ApplicationMapper.toEntity(applicationDto);
        existingApplication.setStudent(updatedData.getStudent());
        existingApplication.setInternship(updatedData.getInternship());

        ApplicationEntity updatedApplication = applicationRepository.save(existingApplication);
        return ApplicationMapper.toDtoResponse(updatedApplication);
    }

    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Transactional
    public ApplicationDtoResponse changeApplicationStatus(
            Long applicationId,
            Long companyId,
            ApplicationStatus newStatus) {

        ApplicationEntity application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Заявка не найдена"));

        // Проверяем, что компания имеет право менять статус этой заявки
        InternshipEntity internship = internshipRepository.findById(application.getInternship().getId())
                .orElseThrow(() -> new EntityNotFoundException("Стажировка не найдена"));

        if (!internship.getCompany().getId().equals(companyId)) {
            throw new AccessDeniedException("Только компания-владелец стажировки может изменять статус заявки");
        }

        // Проверяем допустимость изменения статуса
        if (application.getStatus() != ApplicationStatus.SENT) {
            throw new IllegalStateException("Статус можно изменить только у заявок в статусе SENT");
        }

        application.setStatus(newStatus);
        ApplicationEntity updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.toDtoResponse(updatedApplication);
    }
}