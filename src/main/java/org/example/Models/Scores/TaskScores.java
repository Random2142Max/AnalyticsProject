package org.example.Models.Scores;

import org.example.Models.Courses.Task;

public class TaskScores extends Task{
    private Integer uid;
    private Integer score;

    public TaskScores(Task task, Integer score) {
        super(task);
        this.uid = task.getUid();
        this.score = score;
    }

    public Integer getUid() {
        return this.uid;
    }

    public Integer getScore() {
        return this.score;
    }
}