package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
    private String id;
    private String title;
    private String description;
    private Date deadline;
    private int priority;

    public Task(String id, String title, String description, Date deadline, int priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDeadline = dateFormat.format(getDeadline());
        return "ID: " + getId() + "\n" +
                "Titulo: " + getTitle() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Fecha limite: " + formattedDeadline + "\n" +
                "Prioridad: " + getPriority() + "\n" +
                "=====================================";
    }

    @Override
    public int compareTo(Task other) {
        int priorityComparison = Integer.compare(other.priority, this.priority); // Ordenar de mayor a menor prioridad

        if (priorityComparison != 0) {
            return priorityComparison;
        } else {
            return this.deadline.compareTo(other.deadline);
        }
    }

}