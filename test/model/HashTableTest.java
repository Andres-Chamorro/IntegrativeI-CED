package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import dataEstructures.HashTable;
import java.util.Date;

public class HashTableTest {

	private HashTable<String, Task> taskTable;

	private void setupScenary1() {
		taskTable = new HashTable<>(10);
	}

	@Test
	public void testHashTable() {
		setupScenary1();
	}

	@Test
	public void testPutAndGet() {
		setupScenary1();

		Task task1 = new Task("1", "Task 1", "Description 1", new Date(), 3);
		Task task2 = new Task("2", "Task 2", "Description 2", new Date(), 2);
		Task task3 = new Task("3", "Task 3", "Description 3", new Date(), 1);
		Task task4 = new Task("4", "Task 4", "Description 4", new Date(), 0);

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

		assertEquals("Task 1", taskone.getTitle());
		assertEquals("Task 2", tasktwo.getTitle());
		assertEquals("Task 3", taskthree.getTitle());
		assertEquals("Task 4", taskfour.getTitle());

	}

	@Test
	public void testPutWithMaxSize() {
		int maxSize = 1000;
		HashTable<String, Task> taskTable = new HashTable<>(maxSize);

		for (int i = 0; i < maxSize; i++) {
			Task task = new Task(String.valueOf(i), "Task" + i, "Description " + i, new Date(), i % 4);
			taskTable.put(String.valueOf(i), task);
		}

		for (int i = 0; i < maxSize; i++) {
			Task task = taskTable.get(String.valueOf(i));
			assertNotNull(task);
			assertEquals("Task " + i, task.getTitle());
		}
	}

	@Test
	public void testPutWithCollision() {

		Task task1 = new Task("1", "Task 1", "Description 1", new Date(), 3);
		Task task2 = new Task("11", "Task 11", "Description 11", new Date(), 2);
		taskTable.put("1", task1);
		taskTable.put("11", task2);
		Task retrievedTask1 = taskTable.get("1");
		Task retrievedTask2 = taskTable.get("11");
		assertNotNull(retrievedTask1);
		assertNotNull(retrievedTask2);
		assertEquals("Task 1", retrievedTask1.getTitle());
		assertEquals("Task 11", retrievedTask2.getTitle());
	}

	@Test
	public void testRemove() {
		setupScenary1();

		Task task1 = new Task("1", "Task 1", "Description 1", new Date(), 3);

		taskTable.put("1", task1);

		taskTable.remove("1");

		Task task = taskTable.get("1");

		assertNull(task);
	}

	@Test
	public void testRemoveNonExistent() {
		setupScenary1();

		taskTable.remove("100");

		Task task = taskTable.get("100");

		assertNull(task);
	}

	@Test
	public void testRemoveWithSameHashKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Task 1", "Description 1", new Date(), 3));
		taskTable.put("6", new Task("6", "Task 6", "Description 6", new Date(), 2));

		taskTable.remove("6");

		Task task = taskTable.get("6");

		assertNull(task);
	}

	@Test
	public void testContainsKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Task 1", "Description 1", new Date(), 3));

		assertTrue(taskTable.containsKey("1"));
		assertFalse(taskTable.containsKey("2"));
	}

	@Test
	public void testContainsKeyLimitScenario() {
		setupScenary1();

		for (int i = 1; i <= 10; i++) {
			taskTable.put(String.valueOf(i),
					new Task(String.valueOf(i), "Tarea " + i, "Descripción " + i, new Date(), 3));
		}

		for (int i = 1; i <= 10; i++) {
			assertTrue(taskTable.containsKey(String.valueOf(i)));
		}

		assertFalse(taskTable.containsKey("11"));
	}

	@Test
	public void testCollisionHandLing() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Task 1", "Description 1", new Date(), 3));
		taskTable.put("6", new Task("6", "Task 6", "Description 6", new Date(), 2));

		Task task1 = taskTable.get("1");
		Task task6 = taskTable.get("6");

		assertNotNull(task1);
		assertNotNull(task6);

		assertEquals("Task 1", task1.getTitle());
		assertEquals("Task 6", task6.getTitle());
	}

	@Test
	public void testGetWithNonExistentKey() {
		HashTable<String, Integer> hashTable = new HashTable<>(10);
		assertNull(hashTable.get("hair"));
	}

	@Test
	public void testGetWithSameHashKey() {
		setupScenary1();

		taskTable.put("1", new Task("1", "Task 1", "Description 1", new Date(), 3));
		taskTable.put("6", new Task("6", "Task 6", "Description 6", new Date(), 2));

		Task task = taskTable.get("6");

		assertNotNull(task);
		assertEquals("Task 6", task.getTitle());
	}

}
