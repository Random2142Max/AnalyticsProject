package org.example.Models.Courses;

public class Task {
    private Integer uid;
    private String name;
    private TypeTaskEnum typeTask;
    private Integer maxScore;

    public Task(Integer uid, String name, TypeTaskEnum typeTask, Integer maxScore) {
        this.uid = uid;
        this.name = name;
        this.typeTask = typeTask;
        this.maxScore = maxScore;
    }
    public Task(Task task) {
        this.uid = task.uid;
        this.name = task.name;
        this.typeTask = task.typeTask;
        this.maxScore = task.maxScore;
    }

    public String toString() {
        return String.format("%s — %s — %s", this.name, this.typeTask, this.maxScore);
    }

    public Integer getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public TypeTaskEnum getTypeTask() {
        return this.typeTask;
    }

    public Integer getMaxScore() {
        return this.maxScore;
    }
}

