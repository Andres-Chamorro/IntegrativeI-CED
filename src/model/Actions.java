package model;

public class Actions {

    private Task afterChanges;
    private Task withoutChanges;
    private String actionType;

    public Actions(String actionType, Task afterChanges){

        this.actionType = actionType;
        this.withoutChanges = null;
        this.afterChanges = afterChanges;
    }

    public Actions(String actionType, Task withoutChanges, Task afterChanges){
        this.actionType = actionType;
        this.withoutChanges = withoutChanges;
        this.afterChanges = afterChanges;
    }

    public String getActionType(){
        return this.actionType;
    }
    public Task getAfterTask(){
        return this.afterChanges;
    }
    public Task getBeforeTask(){
        return this.withoutChanges;
    }
    
}
