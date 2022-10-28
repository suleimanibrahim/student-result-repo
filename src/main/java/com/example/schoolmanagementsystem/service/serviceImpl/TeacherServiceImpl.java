package com.example.schoolmanagementsystem.service.serviceImpl;

import com.example.schoolmanagementsystem.dto.StudentDto;
import com.example.schoolmanagementsystem.dto.SubjectDto;
import com.example.schoolmanagementsystem.enums.StudentResult;
import com.example.schoolmanagementsystem.exception.ResultAlreadyComputedException;
import com.example.schoolmanagementsystem.exception.StudentAlreadyExistException;
import com.example.schoolmanagementsystem.exception.StudentNotFoundException;
import com.example.schoolmanagementsystem.exception.SubjectNotBelongToClassException;
import com.example.schoolmanagementsystem.models.StudentSubjects;
import com.example.schoolmanagementsystem.models.Students;
import com.example.schoolmanagementsystem.repository.StudentRepository;
import com.example.schoolmanagementsystem.repository.StudentSubjectRepository;
import com.example.schoolmanagementsystem.respose.BaseResponse;
import com.example.schoolmanagementsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor  @Slf4j
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
            subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
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
                subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

        StudentSubjects averageScore = subjectRepository.findStudentSubjectsByAverageScoreAndTerm(studentSubjects.getAverageScore(), subjectDto.getTerms()).
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
                subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

       StudentSubjects totalScore = subjectRepository.findStudentSubjectsByTotalScoreAndTerm(studentSubjects.getTotalScore(), subjectDto.getTerms()).
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
    public BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentAlreadyExistException, SubjectNotBelongToClassException {
        boolean studentExist = subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).isPresent();

        if(studentExist){
            throw new StudentAlreadyExistException("Student Record In this Term Already Exist Change Term");
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
                studentSubjects.setTerm(subjectDto.getTerms());
                subjectRepository.save(studentSubjects);
            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_A1");

            }

        }

        else if(subjectDto.getStudentClass().equals("CLASS_A2")){
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getEconomics() != null
                    && subjectDto.getArt() != null) {
                studentSubjects.setArt(subjectDto.getArt());
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEconomics(subjectDto.getEconomics());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerm(subjectDto.getTerms());
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
                studentSubjects.setTerm(subjectDto.getTerms());
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
                studentSubjects.setTerm(subjectDto.getTerms());
                subjectRepository.save(studentSubjects);

            }else {
                throw new SubjectNotBelongToClassException("This Subject Does Not Belong to CLASS_B2");

            }
        }


        return new BaseResponse<>(HttpStatus.OK,"Record Added Successfully",null);
    }

    @Override
    public BaseResponse<String> getStudentResult(SubjectDto subjectDto) throws StudentNotFoundException {
//        retrieve the result of any student for a specific subject, class and term //
        StudentSubjects studentSubjects =
                subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

        //Retrieve Student Result of Student in CLASS_A1//
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("writing") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getWriting()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("generalScience") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getGeneralScience()));
        }

        //Retrieve Student Result of Student in CLASS_A2//
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("economics") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getEconomics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("art") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getArt()));
        }


         //Retrieved Result for a specific Student in CLASS_B1//

        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getEnglish()));
        }

        else if(studentSubjects != null && subjectDto.getSubjectName().equals("biology") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getBiology()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("physics") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getPhysics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("chemistry") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getChemistry()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("geography") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getGeography()));
        }


        //Retrieved Result for a specific Student in CLASS_B2//

        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getEnglish()));
        }

        else if(studentSubjects != null && subjectDto.getSubjectName().equals("painting") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getPainting()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("drawing") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getDrawing()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("music") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getMusic()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("accounting") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Result Retrieve Successfully", String.valueOf(studentSubjects.getAccounting()));
        }
        return null;
    }

    @Override
    public List<StudentSubjects> getStudentResultBaseOnTermAndClass(SubjectDto subjectDto) throws StudentNotFoundException {
        List<StudentSubjects> result = new ArrayList<>();
        //get Result Base On Class and Term//
        StudentSubjects studentSubjects =
                subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));



        if(studentSubjects != null && studentSubjects.getStudentName().equals(subjectDto.getStudentName())){
            log.info(studentSubjects.getStudentName());
            result = subjectRepository.
                    findAllByStudentNameAndTermAndStudentClass(subjectDto.getStudentName(), subjectDto.getTerms(), subjectDto.getStudentClass());
        }

        return result;
    }

    @Override
    public List<?> getStudentScore(SubjectDto subjectDto) throws StudentNotFoundException {

        StudentSubjects studentSubjects =
                subjectRepository.findStudentSubjectsByStudentNameAndTerm(subjectDto.getStudentName(), subjectDto.getTerms()).
                        orElseThrow(()-> new StudentNotFoundException("Student with this name not found"));

        List<StudentSubjects> result = new ArrayList<>();
        if((studentSubjects != null) && (studentSubjects.getTerm().equals(subjectDto.getTerms()))){
            result = subjectRepository.findAll();
        }
        return result;
    }



}
