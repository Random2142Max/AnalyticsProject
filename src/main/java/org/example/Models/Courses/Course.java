package org.example.Models.Courses;

import java.util.Collection;
import java.util.List;

public class Course {
    private Integer uid;
    private String name;
    private Collection<Chapter> chapters;
    // Подумать с MaxScores
    private Integer activeMaxScore;
    private Integer exerciseMaxScore;
    private Integer homeworkMaxScore;
    private Integer seminarMaxScore;
    private Integer totalMaxScore;

    public Course(Integer uid, String name, List<Chapter> chapters) {
        this.uid = uid;
        this.name = name;
        this.chapters = chapters;
        // Тут должен быть расчёт totalMaxScore
    }
    public Course(Course course) {
        this.uid = course.uid;
        this.name = course.name;
        this.chapters = course.chapters;
        // Тут должен быть расчёт totalMaxScore
    }

    public Integer getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Chapter> getChapters() {
        return this.chapters;
    }
    public Integer getTotalMaxScore() {return this.totalMaxScore;}

//    public Integer getActiveMaxScore() {
//        return this.activeMaxScore;
//    }
//
//    public Integer getExerciseMaxScore() {
//        return this.exerciseMaxScore;
//    }
//
//    public Integer getHomeworkMaxScore() {
//        return this.homeworkMaxScore;
//    }
//
//    public Integer getSeminarMaxScore() {
//        return this.seminarMaxScore;
//    }
}