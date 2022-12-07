package org.example.Models.Courses;

import java.util.Collection;
import java.util.List;

public class Chapter {
    private Integer uid;
    private String name;
    private Collection<Task> taskCollection;
    // Подумать, как реализовать MaxScores
    private Integer activeMaxScore;
    private Integer exerciseMaxScore;
    private Integer homeworkMaxScore;
    private Integer seminarMaxScore;

    public Chapter(Integer uid, String name, List taskCollection) {
        this.uid = uid;
        this.name = name;
        this.taskCollection = taskCollection;
    }
    public Chapter(Chapter chapter) {
        this.uid = chapter.uid;
        this.name = chapter.name;
        this.taskCollection = chapter.taskCollection;
    }

    public Integer getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Task> getTaskCollection() {
        return this.taskCollection;
    }

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
