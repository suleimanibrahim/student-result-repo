package com.example.schoolmanagementsystem.service;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.enums.StudentResult;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.exception.StudentAlreadyExistException;
import com.example.schoolmanagementsystem.respose.BaseResponse;

public interface TeacherService {

    BaseResponse<String> getStudentScore(Terms terms);

    BaseResponse<String> addStudent(StudentDto studentDto) throws StudentAlreadyExistException;

    BaseResponse<String> addScore(StudentDto studentDto);

    BaseResponse<String> addResulToStudent(StudentResult studentResult);

    BaseResponse<String> getStudentScore(Subject subject);

    BaseResponse<Double> getAverageScore(StudentDto studentDto);



}
