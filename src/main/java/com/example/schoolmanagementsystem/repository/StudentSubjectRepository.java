package com.example.schoolmanagementsystem.repository;

import com.example.schoolmanagementsystem.models.StudentSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubjects, Long> {


    Optional<StudentSubjects> findStudentSubjectsByStudentName(String name);

    Optional<StudentSubjects> findStudentSubjectsByTotalScore(int totalScore);
    Optional<StudentSubjects> findStudentSubjectsByAverageScore(double averageScore);

    Optional<StudentSubjects> findStudentSubjectsByStudentClass(String studentClass);
}
