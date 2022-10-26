package com.example.schoolmanagementsystem.models;

import com.example.schoolmanagementsystem.enums.StudentClass;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter @Entity @RequiredArgsConstructor
public class Students extends Person{

    String score;
    StudentClass studentClass;
    Subject subject;
    Terms terms;
    String result;

}
