package com.example.schoolmanagementsystem.service;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.models.Students;
import com.example.schoolmanagementsystem.respose.BaseResponse;

public interface TeacherService {

    BaseResponse<String> getStudentScore(Terms terms);
    BaseResponse<String> addScore(StudentDto studentDto);

    BaseResponse<String> addResult(StudentDto studentDto);

    BaseResponse<String> getStudentScore(Subject subject);

    BaseResponse<Double> getAverageScore(StudentDto studentDto);



}
