package org.example.Models.Scores;

import org.example.Models.Courses.Course;

public class CourseScores extends Course {
    // Честно говоря, я не понимаю,
    // что должен получать этот класс и хранить
    private Integer uid;
    private Integer totalScores;

    public CourseScores(Course course) {
        super(course);
        this.uid = course.getUid();
        this.totalScores = course.getTotalMaxScore();
    }
    public Integer getUid() {
        return this.uid;
    }
    public Integer getTotalScores() {return this.totalScores;}
}