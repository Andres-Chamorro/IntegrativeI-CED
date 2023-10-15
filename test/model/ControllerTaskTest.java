package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class ControllerTaskTest {

    private ControllerTask controllerTask;

    @Before
    public void setUp() {
        controllerTask = new ControllerTask();
    }

    @Test
    public void testAddTask() {
        Date deadline = new Date();
        String task1 = controllerTask.addTask("horario", "lunes", "clases de 8 a 5", deadline, Priority.ALTA);
        String task2 = controllerTask.addTask("clase", "fisica", "la mejor materia", deadline, Priority.BAJA);
        String task3 = controllerTask.addTask("comida", "frijoles", "comida favorita", deadline, Priority.BAJA);
        String task4 = controllerTask.addTask("mascota", "max", "llevar veterinario", deadline, Priority.ALTA);
        String task5 = controllerTask.addTask("familia", "madre", "ayudar en casa", deadline, Priority.MEDIA);
        String task6 = controllerTask.addTask("saludos", "description", "no saludar hoy", deadline, Priority.BAJA);
        String task7 = controllerTask.addTask("35456", "colegio", "llevar documento mate", deadline, Priority.ALTA);
        String task8 = controllerTask.addTask("1564", "pasaporte", "ir el domingo a reclamar", deadline,
                Priority.MEDIA);
        String task9 = controllerTask.addTask("65489", "amigos", "salir a cine el domingo", deadline, Priority.BAJA);

        assertEquals("Tarea agregada exitosamente.", task1);
        assertEquals("Tarea agregada exitosamente.", task2);
        assertEquals("Tarea agregada exitosamente.", task3);
        assertEquals("Tarea agregada exitosamente.", task4);
        assertEquals("Tarea agregada exitosamente.", task5);
        assertEquals("Tarea agregada exitosamente.", task6);
        assertEquals("Tarea agregada exitosamente.", task7);
        assertEquals("Tarea agregada exitosamente.", task8);
        assertEquals("Tarea agregada exitosamente.", task9);
    }

    @Test
    public void testRemoveTask() {
        testAddTask();
        String delete1 = controllerTask.removeTask("horario");
        String delete2 = controllerTask.removeTask("1564");
        String delete3 = controllerTask.removeTask("saludos");
        String delete4 = controllerTask.removeTask("mascota");
        String delete5 = controllerTask.removeTask("comida");
        String delete6 = controllerTask.removeTask("clase");

        assertEquals("Tarea eliminada exitosamente.", delete1);
        assertEquals("Tarea eliminada exitosamente.", delete2);
        assertEquals("Tarea eliminada exitosamente.", delete3);
        assertEquals("Tarea eliminada exitosamente.", delete4);
        assertEquals("Tarea eliminada exitosamente.", delete5);
        assertEquals("Tarea eliminada exitosamente.", delete6);

    }

    @Test
    public void testModifyTask() {
        testAddTask();
        Date newDeadline = new Date();

        String result = controllerTask.modifyTask("horario", "newtitle", "newdescription", newDeadline, Priority.BAJA);
        String result1 = controllerTask.modifyTask("saludos", "newtitle1", "newdescription1", newDeadline,
                Priority.ALTA);
        String result2 = controllerTask.modifyTask("comida", "newtitle2", "newdescription2", newDeadline,
                Priority.MEDIA);
        String result3 = controllerTask.modifyTask("clase", "newtitle3", "newdescription3", newDeadline, Priority.BAJA);

        assertTrue(result.contains("Tarea modificada exitosamente"));
        assertTrue(result1.contains("Tarea modificada exitosamente"));
        assertTrue(result2.contains("Tarea modificada exitosamente"));
        assertTrue(result3.contains("Tarea modificada exitosamente"));

        assertEquals("newtitle", controllerTask.getTask("horario").getTitle());
        assertEquals("newdescription", controllerTask.getTask("horario").getDescription());
        assertEquals(newDeadline, controllerTask.getTask("horario").getDeadline());
        assertEquals(Priority.BAJA, controllerTask.getTask("horario").getPriority());

        assertEquals("newtitle1", controllerTask.getTask("saludos").getTitle());
        assertEquals("newdescription1", controllerTask.getTask("saludos").getDescription());
        assertEquals(newDeadline, controllerTask.getTask("saludos").getDeadline());
        assertEquals(Priority.ALTA, controllerTask.getTask("saludos").getPriority());

        assertEquals("newtitle2", controllerTask.getTask("comida").getTitle());
        assertEquals("newdescription2", controllerTask.getTask("comida").getDescription());
        assertEquals(newDeadline, controllerTask.getTask("comida").getDeadline());
        assertEquals(Priority.MEDIA, controllerTask.getTask("comida").getPriority());

        assertEquals("newtitle3", controllerTask.getTask("clase").getTitle());
        assertEquals("newdescription3", controllerTask.getTask("clase").getDescription());
        assertEquals(newDeadline, controllerTask.getTask("clase").getDeadline());
        assertEquals(Priority.BAJA, controllerTask.getTask("clase").getPriority());
    }

    @Test
    public void testAddTaskWithNullValues() {
        String taskId = "T4";
        String title = null;
        String description = "Descripción 4";
        Date deadline = null;
        Priority priority = Priority.ALTA;

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