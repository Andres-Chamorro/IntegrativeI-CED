package model;

import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date deadline;
    private boolean isPriority;

    public Task(String title, String description, Date deadline, boolean priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isPriority = priority;
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

    public boolean getIsPriority() {
        return this.isPriority;
    }

    public void setIsPriority(boolean isPriority) {
        this.isPriority = isPriority;
    }

}