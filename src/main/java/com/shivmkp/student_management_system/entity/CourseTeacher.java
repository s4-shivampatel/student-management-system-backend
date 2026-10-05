package com.shivmkp.student_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_teachers",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "course_id",
                        "teacher_id",
                        "academic_year",
                        "semester"
                }
        )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer semester;

    @Column(name = "academic_year", nullable = false, length = 20)
    private String academicYear;

    //<-----RELATIONS----->
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

}