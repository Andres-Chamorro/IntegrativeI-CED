package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerTaskTest {

    private ControllerTask controllerTask;

    @Before
    public void setUp() {
        controllerTask = new ControllerTask();
    }

    @Test
    public void testAddTask() {
        String taskId = "T1";
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date deadline = new Date();
        Priority priority = Priority.ALTA;

        String result = controllerTask.addTask(taskId, title, description, deadline, priority);

        assertEquals("Tarea agregada exitosamente.", result);
        assertTrue(controllerTask.searchTask(taskId));
    }

    @Test
    public void testRemoveTask() {
        String taskId = "T2";
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date deadline = new Date();
        Priority priority = Priority.MEDIA;

        controllerTask.addTask(taskId, title, description, deadline, priority);

        String result = controllerTask.removeTask(taskId);

        assertEquals("Tarea eliminada exitosamente.", result);
        assertFalse(controllerTask.searchTask(taskId));
    }

    @Test
    public void testModifyTask() {
        String taskId = "T3";
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date deadline = new Date();
        Priority priority = Priority.BAJA;

        controllerTask.addTask(taskId, title, description, deadline, priority);

        String newTitle = "Nuevo título";
        String newDescription = "Nueva descripción";
        Date newDeadline = new Date();
        Priority newPriority = Priority.MEDIA;

        String result = controllerTask.modifyTask(taskId, newTitle, newDescription, newDeadline, newPriority);

        assertTrue(result.contains("Tarea modificada exitosamente"));
        assertEquals(newTitle, controllerTask.getTask(taskId).getTitle());
        assertEquals(newDescription, controllerTask.getTask(taskId).getDescription());
        assertEquals(newDeadline, controllerTask.getTask(taskId).getDeadline());
        assertEquals(newPriority, controllerTask.getTask(taskId).getPriority());
    }

    @Test
    public void testAddTaskWithNullValues() {
        String taskId = "T4";
        String title = null;
        String description = "Descripción 4";
        Date deadline = null;
        Priority priority = Priority.ALTA;

        // Agregar la tarea con valores nulos
        String result = controllerTask.addTask(taskId, title, description, deadline, priority);

        assertEquals("Error al agregar la tarea: Todos los campos deben ser proporcionados y no pueden ser nulos.",
                result);
    }

    @Test
    public void testRemoveNonExistentTask() {
        String taskId = "T2";

        // Intentar eliminar una tarea que no existe
        String result = controllerTask.removeTask(taskId);

        assertEquals("La tarea con el ID T2 no existe.", result);
    }

    @Test
    public void testModifyTaskWithNullValues() {
        String taskId = "T3";
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date deadline = new Date();
        Priority priority = Priority.BAJA;

        controllerTask.addTask(taskId, title, description, deadline, priority);

        String result = controllerTask.modifyTask(taskId, null, null, null, null);

        assertTrue(result.contains("No se realizaron modificaciones."));
    }

    @Test
    public void testGetNonExistentTask() {
        String taskId = "T4";

        Task task = controllerTask.getTask(taskId);

        assertNull(task);
    }
}