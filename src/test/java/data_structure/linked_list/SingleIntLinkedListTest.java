package data_structure.linked_list;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingleIntLinkedListTest {
	
	private SingleIntLinkedList uat;
	
	@BeforeEach
    public void setUp() {
		this.uat = new SingleIntLinkedList();
    }
	
	@Test
	void testGetReturnsValueAtIndex() {
		int expected = 2;
		this.uat.insertHead(3);
		this.uat.insertHead(2);
		this.uat.insertHead(1);
		this.uat.insertHead(0);
		
		assertEquals(expected, this.uat.get(expected));
	}
	
	@Test
	void testInsertInsertsAtIndex() {
		int expected = 2;
		int insertIndex = 1;
		this.uat.insertHead(0);
		this.uat.insertHead(1);
		this.uat.insert(expected, insertIndex);

		assertEquals(expected, this.uat.get(insertIndex));
	}
	
	@Test
	void testInsertHeadInsertsValue() {
		int expected = 1;
		
		this.uat.insertHead(expected);

		assertEquals(expected, this.uat.getHead());
	}
	
	@Test
	void testInsertHeadInsertsMultipleValues() {
		int expected = 1;
		this.uat.insertHead(0);
		this.uat.insertHead(expected);

		assertEquals(expected, this.uat.getHead());
	}
	
	@Test
	void testRemoveRemovesAtIndex() {
		int removeValue = 1;
		this.uat.insertHead(2);
		this.uat.insertHead(removeValue);
		this.uat.insertHead(0);
		this.uat.remove(1);
		
		assertNotEquals(removeValue, this.uat.get(1));
	}
	
	@Test
	void testRemoveHeadRemovesOneNode() {
		int headValue = 1;
		this.uat.insertHead(headValue);
		this.uat.removeHead();
		
		assertNotEquals(headValue, this.uat.getHead());
	}

}