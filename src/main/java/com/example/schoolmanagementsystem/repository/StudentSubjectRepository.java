package com.example.schoolmanagementsystem.repository;

import com.example.schoolmanagementsystem.models.StudentSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubjects, Long> {




    Optional<StudentSubjects> findStudentSubjectsByStudentNameAndTerm(String studentName, String term);

    Optional<StudentSubjects> findStudentSubjectsByTotalScoreAndTerm(int totalScore, String term);

    Optional<StudentSubjects> findStudentSubjectsByAverageScoreAndTerm(double averageScore, String term);



    List<StudentSubjects> findAllByStudentNameAndTermAndStudentClass(String studentName, String term, String studentClass);




}
