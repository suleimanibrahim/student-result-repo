package com.example.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
abstract class Person extends Base{

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String dob;
    String address;

}
