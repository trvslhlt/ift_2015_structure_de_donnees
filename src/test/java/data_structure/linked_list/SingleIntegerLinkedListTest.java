package data_structure.linked_list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingleIntegerLinkedListTest {
	
	private SingleIntegerLinkedList uat;
	
	@BeforeEach
    public void setUp() {
		this.uat = new SingleIntegerLinkedList();
    }
	
	@Test
	void testGetReturnsValueAtIndex() {
		int expectedIndex = 2;
		int expectedValue = 10;
		this.uat.addFirst(3);
		this.uat.addFirst(expectedValue);
		this.uat.addFirst(1);
		this.uat.addFirst(0);
		
		assertEquals(expectedValue, this.uat.get(expectedIndex));
	}
	
	@Test
	void testAddAddsAtIndex() {
		int expectedValue = 2;
		int expectedIndex = 1;
		this.uat.addFirst(0);
		this.uat.addFirst(1);
		this.uat.add(expectedValue, expectedIndex);

		assertEquals(expectedValue, this.uat.get(expectedIndex));
	}
	
	@Test
	void testAddFirstAddsValue() {
		int expectedValue = 1;
		
		this.uat.addFirst(expectedValue);

		assertEquals(expectedValue, this.uat.getFirst());
	}
	
	@Test
	void testAddFirstAddsMultipleValues() {
		int expectedValue = 1;
		this.uat.addFirst(0);
		this.uat.addFirst(expectedValue);

		assertEquals(expectedValue, this.uat.getFirst());
	}
	
	@Test
	void testRemoveRemovesAtIndex() {
		int removeValue = 10;
		int removeIndex = 1;
		int expectedValue = 0;
		this.uat.addFirst(expectedValue);
		this.uat.addFirst(removeValue);
		this.uat.addFirst(2);
		this.uat.remove(removeIndex);
		
		assertEquals(expectedValue, this.uat.get(1));
	}
	
	@Test
	void testRemoveFirstRemovesFirstNode() {
		int expectedValue = 1;
		this.uat.addFirst(expectedValue);
		this.uat.removeFirst();
		
		assertNotEquals(expectedValue, this.uat.getFirst());
	}
	
	@Test
	void testIteratorWithForLoop() {
		Integer expectedSum = 6;
		this.uat.addFirst(3);
		this.uat.addFirst(2);
		this.uat.addFirst(1);
		this.uat.addFirst(0);
		
		Integer actualSum = 0;
		for (Integer i : this.uat) {
			actualSum += i;
		}
		
		assertEquals(expectedSum, actualSum);
	}
	
	@Test
	void testIteratorRemove() {
		Integer removeValue = 10;
		this.uat.addFirst(0);
		this.uat.addFirst(removeValue);
		this.uat.addFirst(0);

		Iterator<Integer> it = this.uat.iterator();
		while(it.hasNext()) {
			Integer value = it.next();
			if (value == removeValue) {
				it.remove();
			}
		}
		
		assertEquals(2, this.uat.size());
	}

}