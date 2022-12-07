package org.example.Models.Courses;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

enum TypeTaskEnum {
    ACTIVE,
    EXERCISE,
    HOMEWORK,
    SEMINAR,
    ADDITIONALLY
}

public class TypeTask {
    private TypeTaskEnum typeTask;

    public TypeTask(String typeTask) {
        // Проверка на кодировку, так как ранее она ломалась
        //System.out.println(Charset.defaultCharset());
        switch (typeTask.toUpperCase()) {
            case "АКТ":
                this.typeTask = TypeTaskEnum.ACTIVE;
                break;
            case "УПР":
                this.typeTask = TypeTaskEnum.EXERCISE;
                break;
            case "ДЗ":
                this.typeTask = TypeTaskEnum.HOMEWORK;
                break;
            case "СЕМ":
                this.typeTask = TypeTaskEnum.SEMINAR;
                break;
            case "ДОП":
                this.typeTask = TypeTaskEnum.ADDITIONALLY;
                break;
            default:
                throw new IllegalArgumentException((String.format("В ведомости указан %s. Данный тип заданий отсутствует!", typeTask)));
//                try {
//                    throw new IllegalArgumentException((String.format("В ведомости указан %s. Данный тип заданий отсутствует!", typeTask)));
//                }
//                catch (RuntimeException e) {
//                    System.out.println(typeTask);
//                    e.printStackTrace();
//                }
        }
    }

    public TypeTaskEnum getTypeTask() {
        return this.typeTask;
    }
}
