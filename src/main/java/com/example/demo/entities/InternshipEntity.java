package com.example.demo.entities;

import com.example.demo.enums.InternshipStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_internship")
@Data
@Getter
@Setter
public class InternshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private InternshipStatus status = InternshipStatus.DRAFT;

    @FutureOrPresent
    private LocalDate startDate;

    @Future
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private List<ApplicationEntity> applications;
}