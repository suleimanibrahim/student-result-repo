package com.example.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter @RequiredArgsConstructor @Entity
public class Teacher extends Person{
    String officeAddress;
}
