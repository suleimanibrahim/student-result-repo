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


    private String studentName;

    private String studentClass;

    private String studentResult;

    private String term;

    private Integer mathematics;

    private Integer english;

    private Integer writing;

    private Integer drawing;

    private Integer geography;

    private Integer physics;

    private Integer chemistry;

    private Integer music;

    private Integer painting;

    private Integer biology;

    private Integer economics;

    private Integer art;

    private Integer generalScience;

    private Integer accounting;

    private Integer totalScore;

    private Double averageScore;




}
