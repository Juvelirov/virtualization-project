package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tbl_company")
@Data
@Setter
@Getter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String contactEmail;
    private String contactPhone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<InternshipEntity> internships;
}
