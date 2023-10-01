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

    public String addTask(String title, String description, Date deadline, Priority priority) {
        msg = "";
        try {
            Task task = new Task(title, description, deadline, priority);
            taskTable.put(generateUniqueID(), task);
            msg = "Tarea agregada exitosamente.";
        } catch (Exception e) {
            msg = "Error al agregar la tarea: " + e.getMessage();
        }
        return msg;
    }

    public String removeTask(String taskId) {
        msg = "";

        // Verificar si la tarea existe en la tabla hash
        if (taskTable.containsKey(taskId)) {
            taskTable.remove(taskId);
            msg = "Tarea eliminada exitosamente.";
        } else {
            msg = "La tarea con el ID " + taskId + " no existe.";
        }

        return msg;
    }

    private String generateUniqueID() {

        String uniqueID = "T" + taskIdCounter;
        taskIdCounter++;
        return uniqueID;
    }

}