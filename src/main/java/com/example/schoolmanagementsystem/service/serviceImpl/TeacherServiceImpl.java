package com.example.schoolmanagementsystem.service.serviceImpl;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.enums.StudentResult;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import com.example.schoolmanagementsystem.exception.*;
import com.example.schoolmanagementsystem.models.StudentSubjects;
import com.example.schoolmanagementsystem.models.Students;
import com.example.schoolmanagementsystem.repository.StudentRepository;
import com.example.schoolmanagementsystem.repository.StudentSubjectRepository;
import com.example.schoolmanagementsystem.respose.BaseResponse;
import com.example.schoolmanagementsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;

    private final StudentSubjectRepository subjectRepository;



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
        students.setStudentClass(studentDto.getStudentClass());
        BeanUtils.copyProperties(studentDto, students);
        studentRepository.save(students);

        return new BaseResponse<>(HttpStatus.OK,"Student Added Successfully",null);
    }

    @Override
    public BaseResponse<String> calculateScore(SubjectDto subjectDto) throws StudentNotFoundException {

    StudentSubjects studentSubjects =
            subjectRepository.findStudentSubjectsByStudentName(subjectDto.getStudentName()).
                    orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

    if(studentSubjects != null && studentSubjects.getStudentClass().equals("CLASS_A1")){
        int  totalScore  = studentSubjects.getMathematics() +
                studentSubjects.getEnglish()+
                studentSubjects.getGeneralScience()+studentSubjects.getWriting();
        studentSubjects.setTotalScore(totalScore);
        subjectRepository.save(studentSubjects);
    }
    else if(studentSubjects != null && studentSubjects.getStudentClass().equals("CLASS_A2")){
        int  totalScore  = studentSubjects.getMathematics() +
                studentSubjects.getEnglish()+
                studentSubjects.getArt()+studentSubjects.getEconomics();
        studentSubjects.setTotalScore(totalScore);
        subjectRepository.save(studentSubjects);
    }
    else if(studentSubjects != null && studentSubjects.getStudentClass().equals("CLASS_B1")){
        int  totalScore  =
                studentSubjects.getMathematics() +
                studentSubjects.getEnglish()+
                studentSubjects.getBiology()+
                studentSubjects.getPhysics()+
                studentSubjects.getGeography()+
                studentSubjects.getChemistry();
        studentSubjects.setTotalScore(totalScore);
        subjectRepository.save(studentSubjects);
    }

    else if(studentSubjects != null && studentSubjects.getStudentClass().equals("CLASS_B2")){
        int  totalScore  =
                studentSubjects.getMathematics() +
                        studentSubjects.getEnglish()+
                        studentSubjects.getDrawing()+
                        studentSubjects.getMusic()+
                        studentSubjects.getPainting()+
                        studentSubjects.getAccounting();
        studentSubjects.setTotalScore(totalScore);
        subjectRepository.save(studentSubjects);
    }
        return new BaseResponse<>(HttpStatus.OK,"Total Score Computed Successfully", null);
    }


    @Override
    public BaseResponse<String> calculateResult(SubjectDto subjectDto) throws StudentNotFoundException, ResultAlreadyComputedException, IllegalAccessException {
        StudentSubjects studentSubjects =
                subjectRepository.findStudentSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

        StudentSubjects averageScore = subjectRepository.findStudentSubjectsByAverageScore(studentSubjects.getAverageScore()).
                orElseThrow(()-> new NullPointerException("The value is Null"));

        if(studentSubjects.getStudentResult() != null){
            throw new ResultAlreadyComputedException("Result for this Student Has Already Been Computed");
        }

        if(averageScore != null){

            Double totalAverageScore = studentSubjects.getAverageScore();

            if(totalAverageScore <50 ){
                studentSubjects.setStudentResult(String.valueOf(StudentResult.POOR));
                subjectRepository.save(studentSubjects);
            }
            else if(totalAverageScore >=50 && totalAverageScore <=70 ){
                studentSubjects.setStudentResult(String.valueOf(StudentResult.GOOD));
                subjectRepository.save(studentSubjects);
            }
            else if(totalAverageScore >70 && totalAverageScore <=80 ){
                studentSubjects.setStudentResult(String.valueOf(StudentResult.VERY_GOOD));
                subjectRepository.save(studentSubjects);
            }
            else if(totalAverageScore >80 && totalAverageScore <=100 ){
                studentSubjects.setStudentResult(String.valueOf(StudentResult.EXCELLENT));
                subjectRepository.save(studentSubjects);
            }

        }else{
            throw new IllegalAccessException("Total Average Value is Null compute it first");
        }

        return new BaseResponse<>(HttpStatus.OK,"Student Result Computed Successfully", null);
    }

    @Override
    public BaseResponse<String> calculateAverageScore(SubjectDto subjectDto) throws StudentNotFoundException {
        StudentSubjects studentSubjects =
                subjectRepository.findStudentSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

       StudentSubjects totalScore = subjectRepository.findStudentSubjectsByTotalScore(studentSubjects.getTotalScore()).
               orElseThrow(()-> new NullPointerException("The value is Null"));

      if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_A1")){
          Double averageScore = (double) (studentSubjects.getTotalScore() / 4);
          studentSubjects.setAverageScore(averageScore);
          subjectRepository.save(studentSubjects);
      }
      else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_A2")){
          Double averageScore = (double) (studentSubjects.getTotalScore() / 4);
          studentSubjects.setAverageScore(averageScore);
          subjectRepository.save(studentSubjects);
      }
      else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_B1")){
          Double averageScore = (double) (studentSubjects.getTotalScore() / 6);
          studentSubjects.setAverageScore(averageScore);
          subjectRepository.save(studentSubjects);
      }
      else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_B2")){
          Double averageScore = (double) (studentSubjects.getTotalScore() / 6);
          studentSubjects.setAverageScore(averageScore);
          subjectRepository.save(studentSubjects);
      }
        return new BaseResponse<>(HttpStatus.OK,"Average Score Computed Successfully", null);
    }

    @Override
    public BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentAlreadyExistException, StudentNotFoundException, IllegalAccessException, StudentClassNotFoundException, SubjectNotBelongToClassException {
        boolean studentExist = subjectRepository.findStudentSubjectsByStudentName(subjectDto.getStudentName()).isPresent();

        if(studentExist){
            throw new StudentAlreadyExistException("Student Name Already Exist");
        }
        StudentSubjects studentSubjects = new StudentSubjects();

        if(subjectDto.getStudentClass().equals("CLASS_A1")) {
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getWriting() != null
                    && subjectDto.getGeneralScience() !=null) {
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setWriting(subjectDto.getWriting());
                studentSubjects.setGeneralScience(subjectDto.getGeneralScience());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_A1");

            }

        }

        else if(subjectDto.getStudentClass().equals("CLASS_A2")){
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getEconomics() != null
                    && subjectDto.getArt() !=null) {
                studentSubjects.setArt(subjectDto.getArt());
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEconomics(subjectDto.getEconomics());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_A2");

            }
        }

        else if(subjectDto.getStudentClass().equals("CLASS_B1")) {
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getBiology() != null
                    && subjectDto.getPhysics()
                    !=null && subjectDto.getChemistry() != null
                    && subjectDto.getGeography() !=null ) {

                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setBiology(subjectDto.getBiology());
                studentSubjects.setPhysics(subjectDto.getPhysics());
                studentSubjects.setChemistry(subjectDto.getChemistry());
                studentSubjects.setGeography(subjectDto.getGeography());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_B1");

            }
        }
        else if (subjectDto.getStudentClass().equals("CLASS_B2")){
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getDrawing() != null
                    && subjectDto.getMusic()
                    !=null && subjectDto.getPainting() != null
                    && subjectDto.getAccounting() !=null ) {
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setDrawing(subjectDto.getDrawing());
                studentSubjects.setMusic(subjectDto.getMusic());
                studentSubjects.setPainting(subjectDto.getPainting());
                studentSubjects.setAccounting(subjectDto.getAccounting());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);

            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_B2");

            }
        }


        return new BaseResponse<>(HttpStatus.OK,"Record Added Successfully",null);
    }

    @Override
    public BaseResponse<Double> getAverageScore(StudentDto studentDto) {
        return null;
    }
    @Override
    public BaseResponse<String> getStudentScore(SubjectDto subjectDto) {

        return null;
    }
    @Override
    public BaseResponse<String> getStudentScore(Terms terms) {
        return null;
    }

}
