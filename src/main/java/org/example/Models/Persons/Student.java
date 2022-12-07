package org.example.Models.Persons;

import org.example.Models.StudentScores;

public class Student extends Person {
    private Integer uid;
    private String groupName;

    public Student(Integer uid, String fullname, String groupName) {
        super(fullname);
        this.uid = uid;
        this.groupName = groupName;
    }
    public Student(Student student) {
        super(student.getFullName());
        this.uid = student.getUid();
        this.groupName = student.getGroupName();
    }

    public String toString() {
        return String.format(" %s — %s — %s \n", this.uid, this.getFullName(), this.groupName);
    }

    public String getGroupName() {
        return this.groupName;
    }
}

