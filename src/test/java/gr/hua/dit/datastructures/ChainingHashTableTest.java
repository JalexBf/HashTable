/**
 * This code is part of the lab exercises for the Data Structures course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package gr.hua.dit.datastructures;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import gr.hua.dit.datastructures.HashTable;
import gr.hua.dit.datastructures.Dictionary;

public class ChainingHashTableTest {

	private static final int SIZE = 10000;

	@Test
	public void testChainingHashTable() {

		Dictionary<Integer, Integer> dict = (Dictionary<Integer, Integer>) new HashTable(0);

		List<Integer> values = new ArrayList<>();
		Random rng = new Random(17);
		for (int i = 0; i < SIZE; i++) {
			int n = rng.nextInt(1000);
			values.add(n);
			dict.put(n, n + 1);
		}

		for (Integer v : values) {
			assertTrue(dict.get(v) == v + 1);
		}

	}

}
