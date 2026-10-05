package com.shivmkp.student_management_system.entity;

import com.shivmkp.student_management_system.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
        name = "timetable",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "course_id",
                                "teacher_id",
                                "day",
                                "start_time",
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
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private DayOfWeek day;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(length = 50)
    private String room;

    @Column(nullable = false)
    private Integer semester;

    @Column(name = "academic_year", nullable = false, length = 20)
    private String academicYear;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    //<-----RELATIONS----->
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}