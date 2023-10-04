package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class HashTableTest {

	private HashTable<String, Task> taskTable;
	private Task task;

	private void setupScenary1() {
		taskTable = new HashTable<>(5);
	}

	@Test
	public void testHashTable() {
		setupScenary1();

		String n = "Juan Reyes";
		String e = "jmreyes@icesi.edu.co";
		String p = "3019876543";

	}

}
