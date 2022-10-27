package com.example.schoolmanagementsystem.repository;

import com.example.schoolmanagementsystem.enums.StudentClass;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    Optional<Students> findStudentsByEmail(String email);

    Optional<Students> findStudentsByStudentClass(String studentClass);



//    Optional<Students> findStudentsBySubject(Subject subject);

//    Optional<Students> findStudentsByTerms(Terms terms);






}
