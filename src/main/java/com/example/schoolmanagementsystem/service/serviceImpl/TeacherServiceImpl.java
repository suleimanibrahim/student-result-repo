package com.example.schoolmanagementsystem.service.serviceImpl;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.enums.StudentResult;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.exception.StudentAlreadyExistException;
import com.example.schoolmanagementsystem.models.Students;
import com.example.schoolmanagementsystem.repository.StudentRepository;
import com.example.schoolmanagementsystem.respose.BaseResponse;
import com.example.schoolmanagementsystem.service.TeacherService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Builder
public class TeacherServiceImpl implements TeacherService {

    StudentRepository studentRepository;

    @Override
    public BaseResponse<String> getStudentScore(Terms terms) {
        return null;
    }

    @Override
    public BaseResponse<String> addStudent(StudentDto studentDto) throws StudentAlreadyExistException {
        Students students = new Students();
    boolean studentExist = studentRepository.findStudentsByEmail(studentDto.getEmail()).isPresent();
    if(studentExist){
        throw new StudentAlreadyExistException("Student with this email Already Exist");
    }





        return null;
    }

    @Override
    public BaseResponse<String> addScore(StudentDto studentDto) {

        return null;
    }

    @Override
    public BaseResponse<String> addResulToStudent(StudentResult studentResult) {
        return null;
    }

    @Override
    public BaseResponse<String> getStudentScore(Subject subject) {
        return null;
    }

    @Override
    public BaseResponse<Double> getAverageScore(StudentDto studentDto) {
        return null;
    }
}
