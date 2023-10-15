package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import customExceptions.NullValueException;
import dataEstructures.HashTable;
import dataEstructures.PriorityQueue;
import dataEstructures.Queue;

public class ControllerTask {
    private HashTable<String, Task> taskTable;
    PriorityQueue<Task> priorityTask;
    Queue<Task> nonPriorityQueue;
    private String msg;
    private int taskIdCounter;

    public ControllerTask() {
        taskTable = new HashTable<>(1000);
        priorityTask = new PriorityQueue<>();
        nonPriorityQueue = new Queue<>();
        msg = "";
        taskIdCounter = 1;

    }

    public String addTask(String id, String title, String description, Date deadline, int priority) {
        msg = "";

        try {
            if (id == null || title == null || description == null || deadline == null) {
                throw new NullValueException(
                        "Error al agregar la tarea: Todos los campos deben ser proporcionados y no pueden ser nulos.");
            } else {
                Task task = new Task(id, title, description, deadline, priority);

                taskTable.put(id, task);

                if (priority >= 2) {
                    priorityTask.enqueue(task);
                } else {
                    nonPriorityQueue.enqueue(task);
                }

                msg = "Tarea agregada exitosamente.";
            }
        } catch (NullValueException e) {
            msg = e.getMessage();
        }

        return msg;
    }

    public String removeTask(String taskId) {
        msg = "";

        if (taskTable.containsKey(taskId)) {
            Task task = taskTable.get(taskId);

            taskTable.remove(taskId);

            priorityTask.removeElement(task);

            msg = "Tarea eliminada exitosamente.";

        } else {
            msg = "La tarea con el ID " + taskId + " no existe.";

        }

        return msg;
    }

    public boolean searchTask(String taskId) {
        return taskTable.containsKey(taskId);
    }

    public String modifyTask(String taskId, String newTitle, String newDescription, Date newDeadline,
            int newIsPriority) {
        msg = "";
        Task task = taskTable.get(taskId);

        if (task != null) {
            if (newTitle != null && !newTitle.isEmpty()) {
                task.setTitle(newTitle);
                msg += "\nTítulo modificado.";
            }
            if (newDescription != null && !newDescription.isEmpty()) {
                task.setDescription(newDescription);
                msg += "\nDescripción modificada.";
            }
            if (newDeadline != null) {
                task.setDeadline(newDeadline);
                msg += "\nFecha límite modificada.";
            }

            task.setPriority(newIsPriority);
            msg += "\nPrioridad modificada";

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
        System.out.println("===== Tareas Prioritarias =====");

        if (priorityTask.isEmpty()) {
            System.out.println("No hay tareas prioritarias para mostrar.");
        } else {
            PriorityQueue<Task> tempQueue = new PriorityQueue<>();

            while (!priorityTask.isEmpty()) {
                Task task = priorityTask.dequeue();
                System.out.println(task.toString());
                tempQueue.enqueue(task);
            }

            priorityTask = tempQueue;
        }

        System.out.println("===== Tareas No Prioritarias =====");

        if (nonPriorityQueue.isEmpty()) {
            System.out.println("No hay tareas no prioritarias para mostrar.");
        } else {
            Queue<Task> tempQueue = new Queue<>();

            while (!nonPriorityQueue.isEmpty()) {
                Task task = nonPriorityQueue.dequeue();
                System.out.println(task.toString());
                tempQueue.enqueue(task);
            }

            nonPriorityQueue = tempQueue;
        }

        System.out.println("=================================================");
    }

    public Task getTask(String taskId) {
        Task element = null;
        if (taskTable.containsKey(taskId)) {
            element = taskTable.get(taskId);
        } else {
            element = null;
        }
        return element;
    }

}