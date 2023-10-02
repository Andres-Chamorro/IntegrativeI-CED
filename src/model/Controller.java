package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private HashTable<String, Task> taskTable;
    Queue<Task> priorityQueue;
    Queue<Task> nonPriorityQueue;
    private String msg;
    private int taskIdCounter;

    public Controller() {
        taskTable = new HashTable<>(1000);
        priorityQueue = new Queue<>();
        nonPriorityQueue = new Queue<>();
        msg = "";
        taskIdCounter = 1;

    }

    public String addTask(String title, String description, Date deadline, Priority priority) {
        msg = "";
        try {
            Task task = new Task(title, description, deadline, priority);

            // Agregar la tarea a la HashTable
            taskTable.put(generateUniqueID(), task);

            if (priority == Priority.PRIORITY) {
                priorityQueue.enqueue(task);
            } else {
                nonPriorityQueue.enqueue(task);
            }

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

    public boolean searchTask(String taskId) {
        return taskTable.containsKey(taskId);
    }

    public String modifyTask(String taskId, String newTitle, String newDescription, Date newDeadline,
            Priority newIsPriority) {
        msg = "";
        Task task = taskTable.get(taskId);

        if (task != null) {
            if (!newTitle.isEmpty()) {
                task.setTitle(newTitle);
                msg += "\nTítulo modificado.";
            }
            if (!newDescription.isEmpty()) {
                task.setDescription(newDescription);
                msg += "\nDescripción modificada.";
            }
            if (newDeadline != null) {
                task.setDeadline(newDeadline);
                msg += "\nFecha límite modificada.";
            }
            task.setPriority(newIsPriority);

            if (!msg.isEmpty()) {
                msg = "Tarea modificada exitosamente:" + msg;
            } else {
                msg = "No se realizaron modificaciones.";
            }
        } else {
            msg = "La tarea con el ID " + taskId + " no existe.";
        }

        return msg + "\n";
    }

}