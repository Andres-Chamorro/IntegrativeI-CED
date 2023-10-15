package model;

import java.text.SimpleDateFormat;
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

    // The `public ControllerTask()` constructor initializes the `ControllerTask`
    // object by creating
    // instances of the `HashTable`, `PriorityQueue`, and `Queue` data structures.
    // It also initializes
    // other variables such as `msg`, `taskIdCounter`, and `actionStack`.
    public ControllerTask() {
        taskTable = new HashTable<>(1000);
        priorityTask = new PriorityQueue<>();
        nonPriorityQueue = new Queue<>();
        msg = "";
        taskIdCounter = 1;
        actionStack = new Stack<>();

    }

    /**
     * The addTask function adds a new task to a task table and priority queue, and
     * returns a message
     * indicating the success or failure of the operation.
     * 
     * @param id          The unique identifier for the task.
     * @param title       The title of the task.
     * @param description The description parameter is a String that represents the
     *                    description of the
     *                    task. It provides additional details or information about
     *                    the task.
     * @param deadline    The deadline parameter is of type Date and represents the
     *                    deadline for the task.
     * @param priority    The priority parameter is an integer value that represents
     *                    the priority level of
     *                    the task. A higher priority value indicates a higher
     *                    priority task.
     * @return The method is returning a String message.
     */
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
                Actions action = new Actions("Add task", task);
                actionStack.push(action);
            }
        } catch (NullValueException e) {
            msg = e.getMessage();
        }

        return msg;
    }

    /**
     * The function removes a task from a task table and updates the priority queue
     * and action stack
     * accordingly.
     * 
     * @param taskId The `taskId` parameter is a String that represents the unique
     *               identifier of the
     *               task that needs to be removed.
     * @return The method is returning a String message.
     */
    public String removeTask(String taskId) {
        msg = "";

        if (taskTable.containsKey(taskId)) {
            Task task = taskTable.get(taskId);

            taskTable.remove(taskId);

            priorityTask.removeElement(task);

            if (task.getPriority() <= 1) {
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

    /**
     * The function checks if a task with a given ID exists in a task table.
     * 
     * @param taskId The parameter "taskId" is a String that represents the unique
     *               identifier of a
     *               task.
     * @return The method is returning a boolean value.
     */
    public boolean searchTask(String taskId) {
        return taskTable.containsKey(taskId);
    }

    /**
     * The function modifies a task by updating its title, description, deadline,
     * and priority, and
     * returns a message indicating the success or failure of the modification.
     * 
     * @param taskId         The ID of the task that needs to be modified.
     * @param newTitle       The new title for the task.
     * @param newDescription The new description for the task.
     * @param newDeadline    The new deadline for the task. It is of type Date.
     * @param newIsPriority  The parameter "newIsPriority" is an integer that
     *                       represents the new priority
     *                       of the task. It can have a value of 0 or 1, where 0
     *                       represents a non-priority task and 1
     *                       represents a priority task.
     * @return The method is returning a String message.
     */
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

    /**
     * The function prints the tasks in a priority queue and a non-priority queue,
     * separating them and
     * displaying a message if either queue is empty.
     */
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

    }

    /**
     * The function returns a Task object based on the given taskId, or null if the
     * taskId is not found
     * in the taskTable.
     * 
     * @param taskId The parameter "taskId" is a String that represents the unique
     *               identifier of a task.
     * @return The method is returning a Task object.
     */
    public Task getTask(String taskId) {
        Task element = null;
        if (taskTable.containsKey(taskId)) {
            element = taskTable.get(taskId);
        } else {
            element = null;
        }
        return element;
    }

    /**
     * The `undo()` function pops the last action from the action stack and performs
     * the corresponding
     * undo operation based on the action type.
     */
    public void undo() {
        if (!actionStack.isEmpty()) {
            Actions lastAction = actionStack.pop();
            String actionType = lastAction.getActionType();

            switch (actionType) {
                case "Add task":
                    removeTask(lastAction.getAfterTask().getId());
                    break;
                case "Delete task":
                    addTask(lastAction.getAfterTask().getId(), lastAction.getAfterTask().getTitle(),
                            lastAction.getAfterTask().getDescription(),
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