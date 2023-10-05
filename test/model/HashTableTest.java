package model;

import static org.junit.Assert.*;

import org.junit.Test;
import dataEstructures.HashTable;
import java.util.Date;

public class HashTableTest {

	private HashTable<String, Task> taskTable;

	private void setupScenary1() {
		taskTable = new HashTable<>(5);
	}

	@Test
	public void testHashTable() {
		setupScenary1();
	}

	@Test
	public void testPutAndGet() {
		setupScenary1();

		// Agrega elementos
		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));
		taskTable.put("2", new Task("2", "Tarea 2", "Descripción 2", new Date(), Priority.MEDIA));
		taskTable.put("3", new Task("3", "Tarea 3", "Descripción 3", new Date(), Priority.BAJA));

		// Verifica que los elementos se puedan recuperar correctamente
		Task task1 = taskTable.get("1");
		Task task2 = taskTable.get("2");
		Task task3 = taskTable.get("3");

		assertNotNull(task1);
		assertNotNull(task2);
		assertNotNull(task3);

		assertEquals("Tarea 1", task1.getTitle());
		assertEquals("Tarea 2", task2.getTitle());
		assertEquals("Tarea 3", task3.getTitle());

	}

	@Test
	public void testRemove() {
		setupScenary1();

		// Agrega elementos
		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));

		// Elimina un elemento
		taskTable.remove("1");

		// Intenta obtener el elemento eliminado (debe ser null)
		Task task = taskTable.get("1");

		assertNull(task);
	}

	@Test
	public void testContainsKey() {
		setupScenary1();

		// Agrega un elemento
		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));

		// Verifica si la clave está presente
		assertTrue(taskTable.containsKey("1"));
		assertFalse(taskTable.containsKey("2"));
	}

	@Test
	public void testCollisionHandling() {
		setupScenary1();

		// Agrega elementos que colisionen
		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));
		taskTable.put("6", new Task("6", "Tarea 6", "Descripción 6", new Date(), Priority.MEDIA));

		// Verifica que los elementos se puedan recuperar correctamente
		Task task1 = taskTable.get("1");
		Task task6 = taskTable.get("6");

		assertNotNull(task1);
		assertNotNull(task6);

		assertEquals("Tarea 1", task1.getTitle());
		assertEquals("Tarea 6", task6.getTitle());
	}

}
