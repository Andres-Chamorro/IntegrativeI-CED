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

		Task task1 = new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA);
		Task task2 = new Task("2", "Tarea 2", "Descripción 2", new Date(), Priority.MEDIA);
		Task task3 = new Task("3", "Tarea 3", "Descripción 3", new Date(), Priority.BAJA);
		Task task4 = new Task("4", "Tarea 4", "Descripción 4", new Date(), Priority.ALTA);

		taskTable.put("1", task1);
		taskTable.put("2", task2);
		taskTable.put("3", task3);
		taskTable.put("4", task4);

		Task taskone = taskTable.get("1");
		Task tasktwo = taskTable.get("2");
		Task taskthree = taskTable.get("3");
		Task taskfour = taskTable.get("4");

		assertNotNull(taskone);
		assertNotNull(tasktwo);
		assertNotNull(taskthree);
		assertNotNull(taskfour);

		assertEquals("Tarea 1", taskone.getTitle());
		assertEquals("Tarea 2", tasktwo.getTitle());
		assertEquals("Tarea 3", taskthree.getTitle());
		assertEquals("Tarea 4", taskfour.getTitle());

	}

	@Test
	public void testRemove() {
		setupScenary1();

		Task task1 = new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA);

		taskTable.put("1", task1);

		taskTable.remove("1");

		Task task = taskTable.get("1");

		assertNull(task);
	}

	@Test
	public void testContainsKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));

		assertTrue(taskTable.containsKey("1"));
		assertFalse(taskTable.containsKey("2"));
	}

	@Test
	public void testCollisionHandLing() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));
		taskTable.put("6", new Task("6", "Tarea 6", "Descripción 6", new Date(), Priority.MEDIA));

		Task task1 = taskTable.get("1");
		Task task6 = taskTable.get("6");

		assertNotNull(task1);
		assertNotNull(task6);

		assertEquals("Tarea 1", task1.getTitle());
		assertEquals("Tarea 6", task6.getTitle());
	}

	@Test
	public void testRemoveNonExistent() {
		setupScenary1();

		taskTable.remove("100");

		Task task = taskTable.get("100");

		assertNull(task);
	}

	@Test
	public void testGetWithNonExistentKey() {
		HashTable<String, Integer> hashTable = new HashTable<>(10);
		assertNull(hashTable.get("cuatro"));
	}

	@Test
	public void testGetWithSameHashKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));
		taskTable.put("6", new Task("6", "Tarea 6", "Descripción 6", new Date(), Priority.MEDIA));

		Task task = taskTable.get("6");

		assertNotNull(task);
		assertEquals("Tarea 6", task.getTitle());
	}

	@Test
	public void testRemoveWithSameHashKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Tarea 1", "Descripción 1", new Date(), Priority.ALTA));
		taskTable.put("6", new Task("6", "Tarea 6", "Descripción 6", new Date(), Priority.MEDIA));

		taskTable.remove("6");

		Task task = taskTable.get("6");

		assertNull(task);
	}

}
