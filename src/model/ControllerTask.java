package model;

import java.util.Date;
import java.util.Stack;

import javax.swing.Action;

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
    Stack<Actions> actionStack;

    public ControllerTask() {
        taskTable = new HashTable<>(1000);
        priorityTask = new PriorityQueue<>();
        nonPriorityQueue = new Queue<>();
        msg = "";
        taskIdCounter = 1;
        actionStack = new Stack<>();

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

                if (priority >= 1) {
                    priorityTask.enqueue(task);
                } else {
                    nonPriorityQueue.enqueue(task);
                }

                msg = "Tarea agregada exitosamente.";
                Actions action = new Actions("Add task", task);
                actionStack.push(action);
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
            nonPriorityQueue.dequeue();

            if(task.getPriority() <= 1){
                nonPriorityQueue.dequeue();
            }

            msg = "Tarea eliminada exitosamente.";
            Actions action = new Actions("Delete task", task);
            actionStack.push(action);

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

            if (newIsPriority != task.getPriority()) {
                task.setPriority(newIsPriority);
                msg += "\nPrioridad modificada.";
            }

            if (!msg.isEmpty()) {
                msg = "Tarea modificada exitosamente:" + msg;
                Task aftertask = taskTable.get(taskId);
                Actions action = new Actions("Modify task", task, aftertask);
                actionStack.push(action);
            } else {
                msg = "No se realizaron modificaciones.";
            }
        } else {
            msg = "La tarea con el ID " + taskId + " no existe.";
        }

        return msg + "\n";
    }

    public String getListByPriority() {
        StringBuilder result = new StringBuilder();
        result.append("===== Tareas Prioritarias =====\n");

        if (priorityTask.isEmpty()) {
            result.append("No hay tareas prioritarias para mostrar.\n");
        } else {
            PriorityQueue<Task> tempQueue = new PriorityQueue<>();

            while (!priorityTask.isEmpty()) {
                Task task = priorityTask.dequeue();
                result.append(task.toString()).append("\n");
                tempQueue.enqueue(task);
            }

            priorityTask = tempQueue;
        }

        result.append("===== Tareas No Prioritarias =====\n");

        if (nonPriorityQueue.isEmpty()) {
            result.append("No hay tareas no prioritarias para mostrar.\n");
        } else {
            Queue<Task> tempQueue = new Queue<>();

            while (!nonPriorityQueue.isEmpty()) {
                Task task = nonPriorityQueue.dequeue();
                result.append(task.toString()).append("\n");
                tempQueue.enqueue(task);
            }

            nonPriorityQueue = tempQueue;
        }

        return result.toString();
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

    public void undo() {
        if (!actionStack.isEmpty()) {
            Actions lastAction = actionStack.pop();
            String actionType = lastAction.getActionType();
    
            switch (actionType) {
                case "Add task":
                    removeTask(lastAction.getAfterTask().getId());
                    break;
                case "Delete task":
                    addTask(lastAction.getAfterTask().getId(), lastAction.getAfterTask().getTitle(), lastAction.getAfterTask().getDescription(),
                            lastAction.getAfterTask().getDeadline(), lastAction.getAfterTask().getPriority());
                    break;
                case "Modify Task":
                    modifyTask(lastAction.getBeforeTask().getId(), lastAction.getBeforeTask().getTitle(),
                            lastAction.getBeforeTask().getDescription(), lastAction.getBeforeTask().getDeadline(),
                            lastAction.getBeforeTask().getPriority());
                    break;
            }
        }
    }

}