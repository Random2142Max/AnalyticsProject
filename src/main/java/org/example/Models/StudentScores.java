package org.example.Models;

import org.example.Models.Persons.Student;
import org.example.Models.Scores.CourseScores;

public class StudentScores extends Student {
    private Integer uid;
    private Student student;
    private Integer totalScores;

    public StudentScores(Student student, CourseScores courseScores) {
        super(student);
        this.uid = student.getUid();
        this.student = student;
        this.totalScores = courseScores.getTotalScores();
    }
    public StudentScores(Student student, Integer totalScores) {
        super(student);
        this.uid = student.getUid();
        this.student = student;
        this.totalScores = totalScores;
    }

    public Integer getUid() {
        return this.uid;
    }

    public Student getStudent() {return student;}

    public Integer getTotalScores() {return this.totalScores;}
}

