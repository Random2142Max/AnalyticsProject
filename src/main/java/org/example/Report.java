package org.example;

import org.example.Models.Courses.Task;
import org.example.Models.Courses.TypeTask;
import org.example.Models.Persons.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report {
    private final String url;
    private final Path path;
    private static List<String> fileList = new ArrayList<>();
    private static HashMap<Integer, Student> studentMap = new HashMap<>();
    private static HashMap<Integer,String> readInfoChapterMap = new HashMap<>();
    private static HashMap<Integer, Task> readTaskMap = new HashMap<>();
    public Report(String url) {
        this.url = url;
        this.path = Paths.get(url).toAbsolutePath();
    }
    public void report() {
        // Считываем данные из файла и записываем в List<String>
        try {
            fileList = Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("Ошибка, файл не считался!");
            e.printStackTrace();
        }

        // Методы для работы с файлом
        readWriteAllInfo();
        // Выводим студентов записанных в коллекцию
        outAllInfoConsole();
    }
    // Метод, для считывания и записывания всех данных с файла
    private void readWriteAllInfo() {
        // Запись глав курса
        setFilteredHeader();
        // Запись заданий из глав курса
        setTasks();
        // Запись студентов
        setStudentInformation();
    }
    // Метод для вывода всей информации в консоль
    private void outAllInfoConsole() {
        // Вывод глав курса
        //outFilteredHeader();
        // Вывод заданий
        //outTasks();
        // Вывод студентов
        //outStudentMap();
    }
    private void setFilteredHeader() {

        // Берём заголовок таблицы
        var arrHeading = fileList.get(0).toCharArray();
        System.out.println(" Главы\tКол-во заданий");

        // Объявляем временные переменные для HashMap
        String strHead = "Students";// В первых 2 абзацах информация о студентах
        Integer countSemicolon = 0;

        for (int i = 0; i < arrHeading.length; i++) {
            if (arrHeading[i] == ';') {
                // Тут считаем кол-во абзацев
                countSemicolon++;
            }
            else {
                // Записываем по символьно наименование абзаца
                if (countSemicolon != 0) {
                    // Закидываем во временный HashMap
                    readInfoChapterMap.put(
                            getUid("readInfoChapterMap"),
                            String.format("%s - %s",strHead,countSemicolon));
                    // Обнуляем временные переменные
                    countSemicolon = 0;
                    // Так как у нас уже начинается новое название главы,
                    // чтобы не потерять первую букву названия главы,
                    // мы обнуляем значение и записываем первый символ
                    strHead = "" + arrHeading[i];
                }
                else {
                    // Записываем заголовок по символам
                    strHead += arrHeading[i];
                }
            }
        }
    }
    private void setTasks() {
        // Берём следующие 2 строки Tasks и MaxScoreTasks и немного корректируем внутренние данные для читабельности.
        var massStrTasks = fileList.get(1)
                .replaceAll("[,\\[\\]]","")
                .replace("  ","")
                .replaceFirst(" ","")
                .split(";");

        var massStrMaxScores = fileList.get(2)
                .replaceAll("[, \\[]","")
                .split(";");

        // Для фильтрации по главам, я прохожусь по мапе глав.
        // Создаём общий счётчик, для установления границ, перебора глав
        int countStartChapter = 0;

        for (var chapter : readInfoChapterMap.values()) {
            // Берём название и кол-во заданий в главе
            var arrChapter = chapter.split(" - ");
            var nameChapter = arrChapter[0];
            var countTasks = Integer.parseInt(arrChapter[1]);

            // Проверка на колонку студентов, если бы раздел студентов не находился в начале ведомости.
            // Пытался сделать через == или equals, но эти варианты не работали с текстом "Student",
            // поэтому я решил использовать contains
            if (nameChapter.contains("Students")) {
                // Если это раздел студентов, то мы его пропускаем
                countStartChapter += countTasks;
            }
            else {
                // Теперь, через цикл, можно одновременно работать с 2 массивами строк
                for (int i = countStartChapter; i < countStartChapter + countTasks; i++) {
                    // Тут должно записываться новое задание + его макс. кол-во баллов

                    // С помощью countStartChapter мы берём нужные нам данные,
                    // а также заранее устанавливаем предел, через сумму начальной и конечной границы главы

                    // Создание типов заданий и самих заданий
                    var typeTask = new TypeTask(getTypeTask(massStrTasks[i]));
                    var task = new Task(
                            getUid("readTaskMap"),
                            massStrTasks[i],
                            typeTask.getTypeTask(),
                            Integer.parseInt(massStrMaxScores[i])
                    );
                    readTaskMap.put(
                            getUid("readTaskMap"),
                            task);
                }
                // Сохраняем текущую границу, чтобы избежать повторения и не выходить за конец строки
                countStartChapter += countTasks;
            }
        }
    }
    private void setStudentInformation() {
        // Счётчик для ограничения вывода информации
        Integer count = 0;

        fileList.remove(0); // Удаляем заголовки
        fileList.remove(0); // Удаляем подзаголовки
        fileList.remove(0); // Удаляем максимальные числа

        // Перебираем строки из List<String>
        // Также тут надо поставить ограничение,
        // чтобы метод не сломался, при достижении конца
        for (var item : fileList) {
            // Остановка после получения 1-ых 7-ых студентов
            if (count == 6) break;// Ограничение

            // Работа со списком студентов
            setStudents(item);
            count++;
        }
    }
    private void setStudents(String str) {
        // Проходим по первым 2 столбцам
        Integer countSemicolon = 0;
        // Чтобы не было проблем с памятью, используем StrBuilder
        StringBuilder strBuild = new StringBuilder();
        // Превращаем строку в массив символов и перебираем символы
        for (var _char : str.toCharArray()) {
            if (countSemicolon == 2) {
                break;
            }
            // Если встретилась ';', то check++
            if (_char == ';') {
                countSemicolon++;
            }
            strBuild.append(_char);
        }
        // Разделяем полученную строку на части (массив)
        var arrParagraph = strBuild.toString().split(";");
        // Нужно теперь добавить студента
        studentMap.put(
                getUid("studentMap"),
                new Student(
                        getUid("studentMap"),
                        arrParagraph[0],
                        arrParagraph[1]
                ));
        // Не забываем почистить strBuilder,
        // если собираемся его в дальнейшем использовать
        strBuild.setLength(0);
    }
    private String getTypeTask(String task) {
        if (task.length() > 3) {
            var massTask = task.split(": ");
            return massTask[0];
        }
        else return task;
    }
    private Integer getUid(String map) {
        // Тут откорректировало IDE
        return switch (map) {
            case "studentMap" -> studentMap.isEmpty() ? 0 : studentMap.size();
            case "readInfoChapterMap" -> readInfoChapterMap.isEmpty() ? 0 : readInfoChapterMap.size();
            case "readTaskMap" -> readTaskMap.isEmpty() ? 0 : readTaskMap.size();
            default -> throw new IllegalArgumentException(
                    String.format("Данной %s не существует!", map));
        };
    }
    private static void outFilteredHeader() {
        System.out.println();
        for (var item : readInfoChapterMap.values()) {
            System.out.println(item);
        }
    }
    private static void outTasks() {
        System.out.println("\n Задания ");
        System.out.println(" Name\tType exercise\tMax score\n");
        for (var item : readTaskMap.values()) {
            System.out.println(item.toString());
        }
    }
    private static void outStudentMap() {
        System.out.println("\n Студенты ");
        System.out.println(" Uid\tFull name\tName group\n");
        for (var item : studentMap.values()) {
            System.out.println(item.toString());
        }
    }
    public String getUrl() {return url;}

    public Path getPath() {return path;}
}
