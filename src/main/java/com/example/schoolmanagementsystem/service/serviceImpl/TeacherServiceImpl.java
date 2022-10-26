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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Primary
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;

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
        students.setFirstName(studentDto.getFirstName());
        students.setLastName(studentDto.getLastName());
        students.setEmail(studentDto.getEmail());
        students.setPhoneNumber(studentDto.getPhoneNumber());
        students.setAddress(studentDto.getAddress());
        students.setDob(studentDto.getDob());
        students.setScore(studentDto.getScore());
        students.setResult(studentDto.getResult());
        BeanUtils.copyProperties(studentDto, students);
        studentRepository.save(students);

        return new BaseResponse<>(HttpStatus.OK,"Student Added Successfully",null);
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
