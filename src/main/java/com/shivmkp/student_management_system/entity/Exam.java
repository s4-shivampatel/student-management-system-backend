package com.shivmkp.student_management_system.entity;

import com.shivmkp.student_management_system.enums.ExamType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "exams",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "course_id",
                                "name",
                                "exam_date"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExamType type;

    @OneToMany(
            mappedBy = "exam",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Result> results = new ArrayList<>();
}
