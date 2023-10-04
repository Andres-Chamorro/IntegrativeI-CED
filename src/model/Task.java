package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private Date deadline;
    private Priority priority;

    public Task(String title, String description, Date deadline, Priority priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
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

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDeadline = dateFormat.format(getDeadline());
        return "Titulo: " + getTitle() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Fecha limite: " + formattedDeadline + "\n" +
                "Prioridad: " + getPriority();
    }

    @Override
    public int compareTo(Task other) {
        // Primero, compara por prioridad
        int priorityComparison = this.getPriority().compareTo(other.getPriority());

        if (priorityComparison != 0) {
            return priorityComparison;
        } else {
            // Si las prioridades son iguales, compara por fecha límite
            return this.getDeadline().compareTo(other.getDeadline());
        }
    }

}