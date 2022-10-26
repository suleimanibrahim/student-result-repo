package com.example.schoolmanagementsystem.models;

import com.example.schoolmanagementsystem.enums.StudentClass;
import com.example.schoolmanagementsystem.enums.Subject;
import com.example.schoolmanagementsystem.enums.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter @Entity @AllArgsConstructor @NoArgsConstructor
public class Students extends Person{



    private StudentClass studentClass;

    private Terms terms;





}
