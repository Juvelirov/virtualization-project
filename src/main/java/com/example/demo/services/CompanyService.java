package com.example.demo.services;

import com.example.demo.dto.Company.CompanyDtoResponse;
import com.example.demo.dto.Company.CompanyDtoRequest;
import com.example.demo.entities.CompanyEntity;
import jakarta.persistence.EntityNotFoundException;
import com.example.demo.mapping.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.CompanyRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDtoResponse create(CompanyDtoRequest companyDto) {
        CompanyEntity company = CompanyMapper.toEntity(companyDto);
        CompanyEntity savedCompany = companyRepository.save(company);
        return CompanyMapper.toDtoResponse(savedCompany);
    }

    public CompanyDtoResponse findById(Long id) {
        return companyRepository.findById(id)
                .map(CompanyMapper::toDtoResponse)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена ID: " + id));
    }

    public List<CompanyDtoResponse> findAll() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::toDtoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CompanyDtoResponse update(CompanyDtoRequest companyDto, Long id) {
        CompanyEntity existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена ID: " + id));

        existingCompany.setName(companyDto.getName());
        existingCompany.setDescription(companyDto.getDescription());
        existingCompany.setContactEmail(companyDto.getContactEmail());
        existingCompany.setContactPhone(companyDto.getContactPhone());

        CompanyEntity updatedCompany = companyRepository.save(existingCompany);
        return CompanyMapper.toDtoResponse(updatedCompany);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}