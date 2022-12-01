package org.example.Models.Scores;

import org.example.Models.Courses.Chapter;
import org.example.Models.Courses.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ChapterScores extends Chapter{
    private Integer uid;
//    private Integer chapterScore;

    public ChapterScores(Integer uid, Chapter chapter) {// ,Integer sumScore) {
        super(chapter);
        this.uid = chapter.getUid();
        // Тут надо реализовать сумму chapterScores
        //this.chapterScore = sumScore;
    }

    public Integer getUid() {
        return this.uid;
    }

//    public Integer getChapterScore() {
//        return this.chapterScore;
//    }
}
