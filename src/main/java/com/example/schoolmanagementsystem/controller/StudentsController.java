package com.example.schoolmanagementsystem.controller;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.exception.StudentAlreadyExistException;
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
public class StudentsController {

    private final TeacherServiceImpl teacherService;

    @PostMapping("/addNewStudent")
    BaseResponse<String> addStudent(@RequestBody StudentDto studentDto) throws StudentAlreadyExistException {
        return teacherService.addStudent(studentDto);
    }
}
