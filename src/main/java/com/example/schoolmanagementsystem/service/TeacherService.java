package com.example.schoolmanagementsystem.service;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.exception.*;
import com.example.schoolmanagementsystem.models.StudentSubjects;
import com.example.schoolmanagementsystem.respose.BaseResponse;

import java.util.List;

public interface TeacherService {



    BaseResponse<String> addStudent(StudentDto studentDto) throws StudentAlreadyExistException;

    BaseResponse<String> calculateScore(SubjectDto subjectDto) throws StudentNotFoundException;

    List<?> getStudentScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> calculateResult(SubjectDto subjectDto) throws StudentNotFoundException, ResultAlreadyComputedException, IllegalAccessException;

    BaseResponse<String> calculateAverageScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentNotFoundException, StudentAlreadyExistException, IllegalAccessException, StudentClassNotFoundException, SubjectNotBelongToClassException;

    BaseResponse<String> getStudentResult(SubjectDto subjectDto) throws StudentNotFoundException;

    List<StudentSubjects> getStudentResultBaseOnTermAndClass(SubjectDto subjectDto) throws StudentNotFoundException;



}
