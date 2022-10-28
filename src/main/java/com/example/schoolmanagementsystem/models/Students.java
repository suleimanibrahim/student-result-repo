package com.example.schoolmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter @Entity @AllArgsConstructor @NoArgsConstructor
public class Students extends Person{



    private String studentClass;







}
