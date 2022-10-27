package com.example.schoolmanagementsystem.service;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.enums.StudentResult;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.exception.*;
import com.example.schoolmanagementsystem.respose.BaseResponse;

public interface TeacherService {

    BaseResponse<String> getStudentScore(Terms terms);

    BaseResponse<String> addStudent(StudentDto studentDto) throws StudentAlreadyExistException;

    BaseResponse<String> calculateScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> getStudentScore(SubjectDto subjectDto);

    BaseResponse<String> calculateResult(SubjectDto subjectDto) throws StudentNotFoundException, ResultAlreadyComputedException, IllegalAccessException;

    BaseResponse<String> calculateAverageScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentNotFoundException, StudentAlreadyExistException, IllegalAccessException, StudentClassNotFoundException, SubjectNotBelongToClassException;

    BaseResponse<Double> getAverageScore(StudentDto studentDto);


}
