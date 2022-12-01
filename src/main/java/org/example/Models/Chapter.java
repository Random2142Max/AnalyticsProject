package org.example.Models;

import java.util.ArrayList;

public class Chapter {
    public int Pk_Id;
    public String Chapter;
    public String Fk_CollectionIdTasks;
    public ArrayList<Integer> CollectionId;

    public Chapter(String chapter, String collectionIdTasks) {
        this.Chapter = chapter;
        this.Fk_CollectionIdTasks = collectionIdTasks;
    }
}