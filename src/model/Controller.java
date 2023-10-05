package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import dataEstructures.HashTable;
import dataEstructures.PriorityQueue;
import dataEstructures.Queue;

public class Controller {
    private HashTable<String, Task> taskTable;
    PriorityQueue<Task> priorityTask;
    Queue<Task> nonPriorityQueue;
    private String msg;
    private int taskIdCounter;

    public Controller() {
        taskTable = new HashTable<>(1000);
        priorityTask = new PriorityQueue<>();
        nonPriorityQueue = new Queue<>();
        msg = "";
        taskIdCounter = 1;

    }

    public String addTask(String id, String title, String description, Date deadline, Priority priority) {
        msg = "";
        try {
            Task task = new Task(id, title, description, deadline, priority);

            // Agregar la tarea a la HashTable
            taskTable.put(id, task);

            if (priority == Priority.ALTA) {
                priorityTask.enqueue(task);
            } else if (priority == Priority.MEDIA) {
                priorityTask.enqueue(task);
            } else {
                priorityTask.enqueue(task);
            }

            msg = "Tarea agregada exitosamente.";
        } catch (Exception e) {
            msg = "Error al agregar la tarea: " + e.getMessage();
        }
        return msg;
    }

    public String removeTask(String taskId) {
        msg = "";

        if (taskTable.containsKey(taskId)) {
            Task task = taskTable.get(taskId);

            taskTable.remove(taskId);

            boolean removedFromPriorityQueue = priorityTask.removeElement(task);

            if (removedFromPriorityQueue) {
                msg = "Tarea eliminada exitosamente.";
            } else {
                msg = "La tarea con el ID " + taskId + " no existe.";
            }
        }

        return msg;
    }

    /*
     * private String generateUniqueID() {
     * 
     * String uniqueID = "T" + taskIdCounter;
     * taskIdCounter++;
     * return uniqueID;
     * }
     */

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

    public void printListByPriority() {
        System.out.println("===== Tareas Ordenadas por Prioridad =====");

        if (priorityTask.isEmpty()) {
            System.out.println("No hay tareas prioritarias para mostrar.");
            return;
        }

        PriorityQueue<Task> tempQueue = new PriorityQueue<>();

        while (!priorityTask.isEmpty()) {
            Task task = priorityTask.dequeue();
            System.out.println(task.toString());
            tempQueue.enqueue(task);
        }

        priorityTask = tempQueue;
        System.out.println("=================================================");
    }

}