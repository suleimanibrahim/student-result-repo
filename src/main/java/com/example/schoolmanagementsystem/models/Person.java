package com.example.schoolmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@MappedSuperclass
abstract class Person extends Base{


    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String dob;

    private String address;

    private String email;

}
