package org.example.Models.Courses;

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
                throw new IllegalArgumentException(String.format("В ведомости указан %s. Данный тип заданий отсутствует!", typeTask));
        }
    }

    public TypeTaskEnum getTypeTask() {
        return this.typeTask;
    }
}
