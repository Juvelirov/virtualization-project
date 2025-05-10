package com.example.demo.entities;

import com.example.demo.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_application")
@Data
@Getter
@Setter
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.SENT;

    @CreationTimestamp
    private LocalDateTime appliedAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private InternshipEntity internship;
}