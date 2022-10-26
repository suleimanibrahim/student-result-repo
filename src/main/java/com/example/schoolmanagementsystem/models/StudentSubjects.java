package com.example.schoolmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentSubjects extends Base{


    private String FullName;

    private String mathematics;

    private String english;

    private String writing;

    private String drawing;

    private String geography;

    private String physics;

    private String chemistry;

    private String music;

    private String painting;

    private String biology;

    private String economics;

    private String art;

    private String generalScience;

    private String accounting;

    private String totalScore;

    private String remark;

    private Double averageScore;




}
