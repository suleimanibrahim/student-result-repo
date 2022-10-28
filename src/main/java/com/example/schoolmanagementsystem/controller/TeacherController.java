package com.example.schoolmanagementsystem.controller;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.exception.*;
import com.example.schoolmanagementsystem.models.StudentSubjects;
import com.example.schoolmanagementsystem.respose.BaseResponse;
import com.example.schoolmanagementsystem.service.serviceImpl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getStudentScore")
    BaseResponse<String> getStudentResult(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentResult(subjectDto);
    }
    @GetMapping("/getAllStudentScore")
    List<?> getAllStudentScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentScore(subjectDto);
    }

    @GetMapping("/getStudentResultBaseOnTerm")
    List<StudentSubjects> getStudentResultBaseOnTermAndClass(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentResultBaseOnTermAndClass(subjectDto);
    }



}
