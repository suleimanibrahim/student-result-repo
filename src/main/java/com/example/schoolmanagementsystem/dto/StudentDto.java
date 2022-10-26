package com.example.schoolmanagementsystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentDto {
    String result;
    String score;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String dob;
    String address;

}
