package com.example.schoolmanagementsystem.models;

import com.example.schoolmanagementsystem.enums.StudentClass;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Getter @Setter @Entity @AllArgsConstructor @NoArgsConstructor
public class Students extends Person{



    private String studentClass;


    @OneToMany(targetEntity = StudentSubjects.class, mappedBy = "id")
    private List<StudentSubjects> studentSubjectsList;





}
