package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private HashTable<String, Task> taskTable;
    private String msg;
    private int taskIdCounter;

    public Controller() {
        taskTable = new HashTable<>(1000);
        msg = "";
        taskIdCounter = 1;

    }

    public String addTask(String title, String description, Date deadline, boolean isPriority) {
        msg = "";
        try {
            Task task = new Task(title, description, deadline, isPriority);
            taskTable.put(generateUniqueID(), task);
            msg = "Tarea agregada exitosamente.";
        } catch (Exception e) {
            msg = "Error al agregar la tarea: " + e.getMessage();
        }
        return msg;
    }

    private String generateUniqueID() {

        String uniqueID = "T" + taskIdCounter;
        taskIdCounter++;
        return uniqueID;
    }
}