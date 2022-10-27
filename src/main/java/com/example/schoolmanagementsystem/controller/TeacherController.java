package com.example.schoolmanagementsystem.controller;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.exception.*;
import com.example.schoolmanagementsystem.respose.BaseResponse;
import com.example.schoolmanagementsystem.service.serviceImpl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @PostMapping("/addNewStudent")
    BaseResponse<String> addStudent(@RequestBody StudentDto studentDto) throws StudentAlreadyExistException {
        return teacherService.addStudent(studentDto);
    }

    @PostMapping("/addStudentRecord")
    BaseResponse<String> addStudentRecord(@RequestBody SubjectDto subjectDto) throws StudentAlreadyExistException, StudentNotFoundException, IllegalAccessException, StudentClassNotFoundException, SubjectNotBelongToClassException {
        return teacherService.computeStudentResult(subjectDto);
    }

    @PostMapping("/addTotalScore")
    BaseResponse<String> addTotalScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.calculateScore(subjectDto);
    }

    @PostMapping("/calculateAverageScore")
    BaseResponse<String> calculateAverageScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.calculateAverageScore(subjectDto);
    }


    @PostMapping("/calculateResult")
    BaseResponse<String> calculateResult(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException, ResultAlreadyComputedException, IllegalAccessException {
        return teacherService.calculateResult(subjectDto);
    }
}
