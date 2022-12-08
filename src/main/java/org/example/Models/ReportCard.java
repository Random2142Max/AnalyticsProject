package org.example.Models;

public class ReportCard extends StudentScores{
    private Integer uid;

    public ReportCard(StudentScores studentScores) {
        super(
                studentScores.getStudent(),
                studentScores.getTotalScores());
        this.uid = studentScores.getUid();
    }

    public Integer getUid() {
        return this.uid;
    }
}
