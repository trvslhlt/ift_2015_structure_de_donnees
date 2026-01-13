package data_structure.linked_list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class DoubleLinkedListTest {

	private DoubleLinkedList<String> uat;

	@BeforeEach
	public void setUp() {
		this.uat = new DoubleLinkedList<>();
	}

	@Nested
	@DisplayName("Basic Operations Tests")
	class BasicOperationsTests {

		@Test
		@DisplayName("New list should be empty")
		void testNewListIsEmpty() {
			assertTrue(uat.isEmpty());
			assertEquals(0, uat.size());
		}

		@Test
		@DisplayName("Add single element")
		void testAddSingleElement() {
			assertTrue(uat.add("first"));
			assertFalse(uat.isEmpty());
			assertEquals(1, uat.size());
		}

		@Test
		@DisplayName("Add multiple elements")
		void testAddMultipleElements() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertEquals(3, uat.size());
			assertFalse(uat.isEmpty());
		}

		@Test
		@DisplayName("Clear list")
		void testClear() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			uat.clear();

			assertTrue(uat.isEmpty());
			assertEquals(0, uat.size());
		}

		@Test
		@DisplayName("Clear empty list")
		void testClearEmptyList() {
			uat.clear();
			assertTrue(uat.isEmpty());
			assertEquals(0, uat.size());
		}

		@Test
		@DisplayName("Add null element")
		void testAddNullElement() {
			assertTrue(uat.add(null));
			assertEquals(1, uat.size());
		}
	}

	@Nested
	@DisplayName("Get and Set Operations Tests")
	class GetSetOperationsTests {

		@Test
		@DisplayName("Get element at valid index")
		void testGetAtValidIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertEquals("first", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("third", uat.get(2));
		}

		@Test
		@DisplayName("Get from empty list throws exception")
		void testGetFromEmptyList() {
			assertThrows(IndexOutOfBoundsException.class, () -> uat.get(0));
		}

		@Test
		@DisplayName("Get with negative index throws exception")
		void testGetNegativeIndex() {
			uat.add("first");
			assertThrows(IndexOutOfBoundsException.class, () -> uat.get(-1));
		}

		@Test
		@DisplayName("Get with index >= size throws exception")
		void testGetIndexOutOfBounds() {
			uat.add("first");
			assertThrows(IndexOutOfBoundsException.class, () -> uat.get(1));
		}

		@Test
		@DisplayName("Set element at valid index")
		void testSetAtValidIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String oldValue = uat.set(1, "newSecond");

			assertEquals("second", oldValue);
			assertEquals("newSecond", uat.get(1));
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("Set with invalid index throws exception")
		void testSetInvalidIndex() {
			uat.add("first");
			assertThrows(IndexOutOfBoundsException.class, () -> uat.set(5, "invalid"));
		}
	}

	@Nested
	@DisplayName("Index-Based Add and Remove Tests")
	class IndexBasedAddRemoveTests {

		@Test
		@DisplayName("Add element at beginning")
		void testAddAtBeginning() {
			uat.add("second");
			uat.add("third");
			uat.add(0, "first");

			assertEquals("first", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("third", uat.get(2));
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("Add element at end")
		void testAddAtEnd() {
			uat.add("first");
			uat.add("second");
			uat.add(2, "third");

			assertEquals("third", uat.get(2));
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("Add element in middle")
		void testAddInMiddle() {
			uat.add("first");
			uat.add("third");
			uat.add(1, "second");

			assertEquals("first", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("third", uat.get(2));
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("Add at invalid index throws exception")
		void testAddAtInvalidIndex() {
			uat.add("first");
			assertThrows(IndexOutOfBoundsException.class, () -> uat.add(5, "invalid"));
		}

		@Test
		@DisplayName("Remove element at beginning")
		void testRemoveAtBeginning() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(0);

			assertEquals("first", removed);
			assertEquals("second", uat.get(0));
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("Remove element at end")
		void testRemoveAtEnd() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(2);

			assertEquals("third", removed);
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("Remove element in middle")
		void testRemoveInMiddle() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(1);

			assertEquals("second", removed);
			assertEquals("first", uat.get(0));
			assertEquals("third", uat.get(1));
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("Remove from invalid index throws exception")
		void testRemoveInvalidIndex() {
			uat.add("first");
			assertThrows(IndexOutOfBoundsException.class, () -> uat.remove(5));
		}

		@Test
		@DisplayName("Remove last element makes list empty")
		void testRemoveLastElement() {
			uat.add("only");
			uat.remove(0);

			assertTrue(uat.isEmpty());
			assertEquals(0, uat.size());
		}
	}

	@Nested
	@DisplayName("Remove by Object Tests")
	class RemoveByObjectTests {

		@Test
		@DisplayName("Remove existing element returns true")
		void testRemoveExistingElement() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.remove("second"));
			assertEquals(2, uat.size());
			assertFalse(uat.contains("second"));
		}

		@Test
		@DisplayName("Remove non-existing element returns false")
		void testRemoveNonExistingElement() {
			uat.add("first");
			uat.add("second");

			assertFalse(uat.remove("third"));
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("Remove null element")
		void testRemoveNull() {
			uat.add("first");
			uat.add(null);
			uat.add("third");

			assertTrue(uat.remove(null));
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("Remove first occurrence of duplicate")
		void testRemoveDuplicate() {
			uat.add("first");
			uat.add("second");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.remove("second"));
			assertEquals(3, uat.size());
			assertEquals("second", uat.get(1));
		}
	}

	@Nested
	@DisplayName("Contains and Search Tests")
	class ContainsSearchTests {

		@Test
		@DisplayName("Contains existing element")
		void testContainsExistingElement() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.contains("second"));
		}

		@Test
		@DisplayName("Contains non-existing element")
		void testContainsNonExistingElement() {
			uat.add("first");
			uat.add("second");

			assertFalse(uat.contains("third"));
		}

		@Test
		@DisplayName("Contains null")
		void testContainsNull() {
			uat.add("first");
			uat.add(null);

			assertTrue(uat.contains(null));
		}

		@Test
		@DisplayName("IndexOf existing element")
		void testIndexOfExisting() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertEquals(1, uat.indexOf("second"));
		}

		@Test
		@DisplayName("IndexOf non-existing element")
		void testIndexOfNonExisting() {
			uat.add("first");
			uat.add("second");

			assertEquals(-1, uat.indexOf("third"));
		}

		@Test
		@DisplayName("IndexOf returns first occurrence")
		void testIndexOfFirstOccurrence() {
			uat.add("first");
			uat.add("second");
			uat.add("second");
			uat.add("third");

			assertEquals(1, uat.indexOf("second"));
		}

		@Test
		@DisplayName("LastIndexOf existing element")
		void testLastIndexOfExisting() {
			uat.add("first");
			uat.add("second");
			uat.add("second");
			uat.add("third");

			assertEquals(2, uat.lastIndexOf("second"));
		}

		@Test
		@DisplayName("LastIndexOf non-existing element")
		void testLastIndexOfNonExisting() {
			uat.add("first");
			uat.add("second");

			assertEquals(-1, uat.lastIndexOf("third"));
		}

		@Test
		@DisplayName("IndexOf and LastIndexOf for single occurrence")
		void testIndexOfAndLastIndexOfSingle() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertEquals(uat.indexOf("second"), uat.lastIndexOf("second"));
		}
	}

	@Nested
	@DisplayName("Collection Operations Tests")
	class CollectionOperationsTests {

		@Test
		@DisplayName("AddAll at end")
		void testAddAllAtEnd() {
			uat.add("first");
			uat.add("second");

			List<String> toAdd = Arrays.asList("third", "fourth", "fifth");
			assertTrue(uat.addAll(toAdd));

			assertEquals(5, uat.size());
			assertEquals("third", uat.get(2));
			assertEquals("fourth", uat.get(3));
			assertEquals("fifth", uat.get(4));
		}

		@Test
		@DisplayName("AddAll empty collection returns false")
		void testAddAllEmpty() {
			uat.add("first");

			List<String> toAdd = new ArrayList<>();
			assertFalse(uat.addAll(toAdd));
			assertEquals(1, uat.size());
		}

		@Test
		@DisplayName("AddAll at specific index")
		void testAddAllAtIndex() {
			uat.add("first");
			uat.add("fourth");

			List<String> toAdd = Arrays.asList("second", "third");
			assertTrue(uat.addAll(1, toAdd));

			assertEquals(4, uat.size());
			assertEquals("first", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("third", uat.get(2));
			assertEquals("fourth", uat.get(3));
		}

		@Test
		@DisplayName("AddAll at beginning")
		void testAddAllAtBeginning() {
			uat.add("third");
			uat.add("fourth");

			List<String> toAdd = Arrays.asList("first", "second");
			assertTrue(uat.addAll(0, toAdd));

			assertEquals(4, uat.size());
			assertEquals("first", uat.get(0));
			assertEquals("second", uat.get(1));
		}

		@Test
		@DisplayName("ContainsAll with all elements present")
		void testContainsAllTrue() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			List<String> toCheck = Arrays.asList("first", "third");
			assertTrue(uat.containsAll(toCheck));
		}

		@Test
		@DisplayName("ContainsAll with some elements missing")
		void testContainsAllFalse() {
			uat.add("first");
			uat.add("second");

			List<String> toCheck = Arrays.asList("first", "third");
			assertFalse(uat.containsAll(toCheck));
		}

		@Test
		@DisplayName("RemoveAll removes matching elements")
		void testRemoveAll() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");

			List<String> toRemove = Arrays.asList("second", "fourth");
			assertTrue(uat.removeAll(toRemove));

			assertEquals(2, uat.size());
			assertFalse(uat.contains("second"));
			assertFalse(uat.contains("fourth"));
			assertTrue(uat.contains("first"));
			assertTrue(uat.contains("third"));
		}

		@Test
		@DisplayName("RemoveAll with no matches returns false")
		void testRemoveAllNoMatches() {
			uat.add("first");
			uat.add("second");

			List<String> toRemove = Arrays.asList("third", "fourth");
			assertFalse(uat.removeAll(toRemove));
			assertEquals(2, uat.size());
		}

		@Test
		@DisplayName("RetainAll keeps only matching elements")
		void testRetainAll() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");

			List<String> toRetain = Arrays.asList("second", "fourth");
			assertTrue(uat.retainAll(toRetain));

			assertEquals(2, uat.size());
			assertTrue(uat.contains("second"));
			assertTrue(uat.contains("fourth"));
			assertFalse(uat.contains("first"));
			assertFalse(uat.contains("third"));
		}

		@Test
		@DisplayName("RetainAll with all elements returns false")
		void testRetainAllNoChange() {
			uat.add("first");
			uat.add("second");

			List<String> toRetain = Arrays.asList("first", "second");
			assertFalse(uat.retainAll(toRetain));
			assertEquals(2, uat.size());
		}
	}

	@Nested
	@DisplayName("Iterator Tests")
	class IteratorTests {

		@Test
		@DisplayName("Iterator hasNext and next")
		void testIteratorBasic() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Iterator<String> iterator = uat.iterator();

			assertTrue(iterator.hasNext());
			assertEquals("first", iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals("second", iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals("third", iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("Iterator on empty list")
		void testIteratorEmpty() {
			Iterator<String> iterator = uat.iterator();
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("Iterator next on exhausted iterator throws exception")
		void testIteratorNoSuchElement() {
			uat.add("first");
			Iterator<String> iterator = uat.iterator();
			iterator.next();

			assertThrows(NoSuchElementException.class, iterator::next);
		}

		@Test
		@DisplayName("Iterator remove")
		void testIteratorRemove() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Iterator<String> iterator = uat.iterator();
			iterator.next();
			iterator.remove();

			assertEquals(2, uat.size());
			assertFalse(uat.contains("first"));
		}

		@Test
		@DisplayName("ListIterator forward traversal")
		void testListIteratorForward() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator();

			assertTrue(iterator.hasNext());
			assertEquals(0, iterator.nextIndex());
			assertEquals("first", iterator.next());
			assertEquals("second", iterator.next());
			assertEquals("third", iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("ListIterator backward traversal")
		void testListIteratorBackward() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator(3);

			assertTrue(iterator.hasPrevious());
			assertEquals(2, iterator.previousIndex());
			assertEquals("third", iterator.previous());
			assertEquals("second", iterator.previous());
			assertEquals("first", iterator.previous());
			assertFalse(iterator.hasPrevious());
		}

		@Test
		@DisplayName("ListIterator bidirectional traversal")
		void testListIteratorBidirectional() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator();

			assertEquals("first", iterator.next());
			assertEquals("second", iterator.next());
			assertEquals("second", iterator.previous());
			assertEquals("second", iterator.next());
			assertEquals("third", iterator.next());
		}

		@Test
		@DisplayName("ListIterator set")
		void testListIteratorSet() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator();
			iterator.next();
			iterator.set("newFirst");

			assertEquals("newFirst", uat.get(0));
		}

		@Test
		@DisplayName("ListIterator add")
		void testListIteratorAdd() {
			uat.add("first");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator();
			iterator.next();
			iterator.add("second");

			assertEquals(3, uat.size());
			assertEquals("second", uat.get(1));
		}
	}

	@Nested
	@DisplayName("Array Conversion Tests")
	class ArrayConversionTests {

		@Test
		@DisplayName("ToArray returns correct array")
		void testToArray() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Object[] array = uat.toArray();

			assertEquals(3, array.length);
			assertEquals("first", array[0]);
			assertEquals("second", array[1]);
			assertEquals("third", array[2]);
		}

		@Test
		@DisplayName("ToArray on empty list")
		void testToArrayEmpty() {
			Object[] array = uat.toArray();
			assertEquals(0, array.length);
		}

		@Test
		@DisplayName("ToArray with typed array of sufficient size")
		void testToArrayTyped() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String[] array = new String[3];
			String[] result = uat.toArray(array);

			assertSame(array, result);
			assertEquals("first", result[0]);
			assertEquals("second", result[1]);
			assertEquals("third", result[2]);
		}

		@Test
		@DisplayName("ToArray with typed array too small")
		void testToArrayTypedSmall() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String[] array = new String[1];
			String[] result = uat.toArray(array);

			assertNotSame(array, result);
			assertEquals(3, result.length);
			assertEquals("first", result[0]);
			assertEquals("second", result[1]);
			assertEquals("third", result[2]);
		}

		@Test
		@DisplayName("ToArray with typed array larger than list")
		void testToArrayTypedLarge() {
			uat.add("first");
			uat.add("second");

			String[] array = new String[5];
			String[] result = uat.toArray(array);

			assertSame(array, result);
			assertEquals("first", result[0]);
			assertEquals("second", result[1]);
			assertNull(result[2]);
		}
	}

	@Nested
	@DisplayName("SubList Tests")
	class SubListTests {

		@Test
		@DisplayName("SubList basic operation")
		void testSubList() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");
			uat.add("fifth");

			List<String> subList = uat.subList(1, 4);

			assertEquals(3, subList.size());
			assertEquals("second", subList.get(0));
			assertEquals("third", subList.get(1));
			assertEquals("fourth", subList.get(2));
		}

		@Test
		@DisplayName("SubList empty range")
		void testSubListEmpty() {
			uat.add("first");
			uat.add("second");

			List<String> subList = uat.subList(1, 1);
			assertTrue(subList.isEmpty());
		}

		@Test
		@DisplayName("SubList invalid range throws exception")
		void testSubListInvalidRange() {
			uat.add("first");
			uat.add("second");

			assertThrows(IndexOutOfBoundsException.class, () -> uat.subList(2, 1));
		}

		@Test
		@DisplayName("SubList modifications affect original list")
		void testSubListModifications() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			List<String> subList = uat.subList(0, 2);
			subList.set(0, "newFirst");

			assertEquals("newFirst", uat.get(0));
		}
	}

	@Nested
	@DisplayName("Edge Cases and Special Scenarios")
	class EdgeCasesTests {

		@Test
		@DisplayName("Multiple adds and removes")
		void testMultipleAddsAndRemoves() {
			uat.add("first");
			uat.add("second");
			uat.remove(0);
			uat.add("third");
			uat.remove("second");
			uat.add(0, "zero");

			assertEquals(2, uat.size());
			assertEquals("zero", uat.get(0));
			assertEquals("third", uat.get(1));
		}

		@Test
		@DisplayName("List with duplicate elements")
		void testDuplicateElements() {
			uat.add("first");
			uat.add("second");
			uat.add("first");
			uat.add("second");

			assertEquals(4, uat.size());
			assertEquals(0, uat.indexOf("first"));
			assertEquals(2, uat.lastIndexOf("first"));
		}

		@Test
		@DisplayName("List with null elements")
		void testNullElements() {
			uat.add(null);
			uat.add("second");
			uat.add(null);

			assertEquals(3, uat.size());
			assertNull(uat.get(0));
			assertNull(uat.get(2));
			assertTrue(uat.contains(null));
			assertEquals(0, uat.indexOf(null));
			assertEquals(2, uat.lastIndexOf(null));
		}

		@Test
		@DisplayName("Add and remove all elements one by one")
		void testAddRemoveAllElements() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			uat.remove(2);
			uat.remove(1);
			uat.remove(0);

			assertTrue(uat.isEmpty());
			assertEquals(0, uat.size());
		}

		@Test
		@DisplayName("Large number of elements")
		void testLargeNumberOfElements() {
			int count = 1000;
			for (int i = 0; i < count; i++) {
				uat.add("element" + i);
			}

			assertEquals(count, uat.size());
			assertEquals("element0", uat.get(0));
			assertEquals("element999", uat.get(999));
			assertEquals("element500", uat.get(500));
		}

		@Test
		@DisplayName("Interleaved operations")
		void testInterleavedOperations() {
			uat.add("a");
			uat.add("b");
			assertEquals(2, uat.size());

			uat.add(1, "c");
			assertEquals("c", uat.get(1));

			uat.remove("b");
			assertEquals(2, uat.size());

			uat.set(0, "x");
			assertEquals("x", uat.get(0));

			uat.clear();
			assertTrue(uat.isEmpty());
		}
	}
}
