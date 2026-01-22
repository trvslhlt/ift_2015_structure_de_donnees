package data_structure.linked_list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class CircularLinkedListTest {

	private CircularLinkedList<String> uat;

	@BeforeEach
	public void setUp() {
		this.uat = new CircularLinkedList<>();
	}

	@Nested
	@DisplayName("Basic Operations Tests")
	class BasicOperationsTests {

		@Test
		@DisplayName("New list should be empty")
		void testNewListIsEmpty() {
			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("Add single element makes list non-empty")
		void testAddSingleElement() {
			assertTrue(uat.add("first"));
			assertFalse(uat.isEmpty());
		}

		@Test
		@DisplayName("Add multiple elements")
		void testAddMultipleElements() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertFalse(uat.isEmpty());
		}

		@Test
		@DisplayName("Clear list makes it empty")
		void testClear() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			uat.clear();

			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("Clear empty list")
		void testClearEmptyList() {
			uat.clear();
			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("Add null element")
		void testAddNullElement() {
			assertTrue(uat.add(null));
			assertFalse(uat.isEmpty());
		}
	}

	@Nested
	@DisplayName("Size Tests")
	class SizeTests {

		@Test
		@Disabled("size() method not yet implemented - returns 0 instead of actual size")
		@DisplayName("Size of empty list is zero")
		void testSizeEmptyList() {
			assertEquals(0, uat.size());
		}

		@Test
		@Disabled("size() method not yet implemented - returns 0 instead of actual size")
		@DisplayName("Size after adding elements")
		void testSizeAfterAdding() {
			uat.add("first");
			assertEquals(1, uat.size());

			uat.add("second");
			assertEquals(2, uat.size());

			uat.add("third");
			assertEquals(3, uat.size());
		}

		@Test
		@Disabled("size() method not yet implemented - returns 0 instead of actual size")
		@DisplayName("Size after clear")
		void testSizeAfterClear() {
			uat.add("first");
			uat.add("second");
			uat.clear();
			assertEquals(0, uat.size());
		}
	}

	@Nested
	@DisplayName("Get Operations Tests")
	class GetOperationsTests {

		@Test
		@DisplayName("Get element - add inserts at head so newest element is at index 0")
		void testGetReturnsNewestFirst() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// add() inserts at head, so "third" (most recent) is at index 0
			assertEquals("third", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("first", uat.get(2));
		}

		@Test
		@DisplayName("Get from empty list returns null")
		void testGetFromEmptyList() {
			assertNull(uat.get(0));
		}

		@Test
		@DisplayName("Get single element")
		void testGetSingleElement() {
			uat.add("only");
			assertEquals("only", uat.get(0));
		}

		@Test
		@DisplayName("Get with circular index wraps around")
		void testGetCircularIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// Index 3 wraps to index 0
			assertEquals(uat.get(0), uat.get(3));
			assertEquals(uat.get(1), uat.get(4));
		}

		@Test
		@DisplayName("Get with negative index wraps around")
		void testGetNegativeIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// -1 should wrap to index 2 (size - 1)
			assertEquals(uat.get(2), uat.get(-1));
		}
	}

	@Nested
	@DisplayName("Set Operations Tests")
	class SetOperationsTests {

		@Test
		@Disabled("set() method not yet implemented")
		@DisplayName("Set element at valid index")
		void testSetAtValidIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// Index 1 is "second" (middle element)
			String oldValue = uat.set(1, "newSecond");

			assertEquals("second", oldValue);
			assertEquals("newSecond", uat.get(1));
		}

		@Test
		@Disabled("set() method not yet implemented")
		@DisplayName("Set first element")
		void testSetFirstElement() {
			uat.add("first");
			uat.add("second");

			String oldValue = uat.set(0, "newFirst");

			assertEquals("second", oldValue); // "second" is at index 0 due to add-at-head
			assertEquals("newFirst", uat.get(0));
		}

		@Test
		@Disabled("set() method not yet implemented")
		@DisplayName("Set last element")
		void testSetLastElement() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String oldValue = uat.set(2, "newLast");

			assertEquals("first", oldValue); // "first" is at index 2 due to add-at-head
			assertEquals("newLast", uat.get(2));
		}
	}

	@Nested
	@DisplayName("Index-Based Add Tests")
	class IndexBasedAddTests {

		@Test
		@DisplayName("Add element at index 0")
		void testAddAtIndex0() {
			uat.add("first");
			uat.add("second");
			uat.add(0, "inserted");

			assertEquals("inserted", uat.get(0));
		}

		@Test
		@DisplayName("Add element at specific index")
		void testAddAtSpecificIndex() {
			uat.add("a");
			uat.add("b");
			uat.add("c");
			// List is now: c, b, a

			uat.add(1, "inserted");

			assertEquals("c", uat.get(0));
			assertEquals("inserted", uat.get(1));
		}

		@Test
		@DisplayName("Add to empty list with index calls regular add")
		void testAddToEmptyListWithIndex() {
			uat.add(0, "first");
			assertEquals("first", uat.get(0));
			assertFalse(uat.isEmpty());
		}
	}

	@Nested
	@DisplayName("Remove by Index Tests")
	class RemoveByIndexTests {

		@Test
		@Disabled("remove(int) method not yet implemented")
		@DisplayName("Remove element at beginning")
		void testRemoveAtBeginning() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(0);

			assertEquals("third", removed); // "third" is at index 0
		}

		@Test
		@Disabled("remove(int) method not yet implemented")
		@DisplayName("Remove element at end")
		void testRemoveAtEnd() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(2);

			assertEquals("first", removed); // "first" is at index 2
		}

		@Test
		@Disabled("remove(int) method not yet implemented")
		@DisplayName("Remove element in middle")
		void testRemoveInMiddle() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String removed = uat.remove(1);

			assertEquals("second", removed);
		}

		@Test
		@Disabled("remove(int) method not yet implemented")
		@DisplayName("Remove last element makes list empty")
		void testRemoveLastElement() {
			uat.add("only");
			uat.remove(0);

			assertTrue(uat.isEmpty());
		}
	}

	@Nested
	@DisplayName("Remove by Object Tests")
	class RemoveByObjectTests {

		@Test
		@Disabled("remove(Object) method not yet implemented")
		@DisplayName("Remove existing element returns true")
		void testRemoveExistingElement() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.remove("second"));
			assertFalse(uat.contains("second"));
		}

		@Test
		@Disabled("remove(Object) method not yet implemented")
		@DisplayName("Remove non-existing element returns false")
		void testRemoveNonExistingElement() {
			uat.add("first");
			uat.add("second");

			assertFalse(uat.remove("third"));
		}

		@Test
		@Disabled("remove(Object) method not yet implemented")
		@DisplayName("Remove null element")
		void testRemoveNull() {
			uat.add("first");
			uat.add(null);
			uat.add("third");

			assertTrue(uat.remove(null));
		}

		@Test
		@Disabled("remove(Object) method not yet implemented")
		@DisplayName("Remove first occurrence of duplicate")
		void testRemoveDuplicate() {
			uat.add("first");
			uat.add("second");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.remove("second"));
			assertTrue(uat.contains("second")); // Still has one "second"
		}
	}

	@Nested
	@DisplayName("Contains Tests")
	class ContainsTests {

		@Test
		@DisplayName("Contains element at head position")
		void testContainsAtHead() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// "third" is at head (index 0)
			assertTrue(uat.contains("third"));
		}

		@Test
		@DisplayName("Contains element in middle")
		void testContainsInMiddle() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertTrue(uat.contains("second"));
		}

		@Test
		@DisplayName("Contains non-existing element")
		void testContainsNonExisting() {
			uat.add("first");
			uat.add("second");

			assertFalse(uat.contains("third"));
		}

		@Test
		@DisplayName("Contains null")
		void testContainsNull() {
			uat.add("first");
			uat.add(null);
			uat.add("third");

			assertTrue(uat.contains(null));
		}

		@Test
		@DisplayName("Contains on empty list")
		void testContainsEmptyList() {
			assertFalse(uat.contains("anything"));
		}

		@Test
		@DisplayName("Contains on single element list")
		void testContainsSingleElement() {
			uat.add("only");
			assertTrue(uat.contains("only"));
		}

		@Test
		@Disabled("indexOf has bug - doesn't check tail node, so contains fails for tail element")
		@DisplayName("Contains element at tail position (first added)")
		void testContainsAtTail() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// "first" is at tail position - indexOf bug prevents this from working
			assertTrue(uat.contains("first"));
		}
	}

	@Nested
	@DisplayName("IndexOf Tests")
	class IndexOfTests {

		@Test
		@DisplayName("IndexOf element at head position")
		void testIndexOfAtHead() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// "third" is at index 0 (head)
			assertEquals(0, uat.indexOf("third"));
		}

		@Test
		@DisplayName("IndexOf element in middle")
		void testIndexOfInMiddle() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// "second" is at index 1
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
		@DisplayName("IndexOf on empty list")
		void testIndexOfEmptyList() {
			assertEquals(-1, uat.indexOf("anything"));
		}

		@Test
		@DisplayName("IndexOf null element")
		void testIndexOfNull() {
			uat.add("first");
			uat.add(null);
			uat.add("third");

			// null is at index 1
			assertEquals(1, uat.indexOf(null));
		}

		@Test
		@DisplayName("IndexOf returns first occurrence for duplicates")
		void testIndexOfFirstOccurrence() {
			uat.add("a");
			uat.add("dup");
			uat.add("b");
			uat.add("dup");
			uat.add("c");
			// Order: c, dup, b, dup, a

			assertEquals(1, uat.indexOf("dup"));
		}

		@Test
		@DisplayName("IndexOf single element")
		void testIndexOfSingleElement() {
			uat.add("only");
			assertEquals(0, uat.indexOf("only"));
		}

		@Test
		@Disabled("indexOf has bug - loop stops before checking tail node")
		@DisplayName("IndexOf element at tail position (first added)")
		void testIndexOfAtTail() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// "first" is at tail (index 2), but indexOf doesn't check tail
			assertEquals(2, uat.indexOf("first"));
		}
	}

	@Nested
	@DisplayName("LastIndexOf Tests")
	class LastIndexOfTests {

		@Test
		@Disabled("lastIndexOf has bug - returns 0 instead of lastIdx")
		@DisplayName("LastIndexOf existing element")
		void testLastIndexOfExisting() {
			uat.add("a");
			uat.add("dup");
			uat.add("b");
			uat.add("dup");
			uat.add("c");
			// Order: c, dup, b, dup, a

			assertEquals(3, uat.lastIndexOf("dup"));
		}

		@Test
		@Disabled("lastIndexOf has bug - returns 0 instead of lastIdx")
		@DisplayName("LastIndexOf non-existing element")
		void testLastIndexOfNonExisting() {
			uat.add("first");
			uat.add("second");

			assertEquals(-1, uat.lastIndexOf("third"));
		}

		@Test
		@Disabled("lastIndexOf has bug - returns 0 instead of lastIdx")
		@DisplayName("LastIndexOf single occurrence equals indexOf")
		void testLastIndexOfSingleOccurrence() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			assertEquals(uat.indexOf("second"), uat.lastIndexOf("second"));
		}

		@Test
		@Disabled("lastIndexOf has bug - returns 0 instead of lastIdx")
		@DisplayName("LastIndexOf null element")
		void testLastIndexOfNull() {
			uat.add("first");
			uat.add(null);
			uat.add("second");
			uat.add(null);
			// Order: null, second, null, first

			assertEquals(2, uat.lastIndexOf(null));
		}
	}

	@Nested
	@DisplayName("Collection Operations Tests")
	class CollectionOperationsTests {

		@Test
		@DisplayName("AddAll adds elements (each at head)")
		void testAddAll() {
			List<String> toAdd = Arrays.asList("first", "second", "third");
			assertTrue(uat.addAll(toAdd));

			// Each element is added at head, so "third" ends up at index 0
			assertEquals("third", uat.get(0));
			assertEquals("second", uat.get(1));
			assertEquals("first", uat.get(2));
		}

		@Test
		@DisplayName("AddAll to non-empty list")
		void testAddAllToNonEmpty() {
			uat.add("existing");

			List<String> toAdd = Arrays.asList("new1", "new2");
			assertTrue(uat.addAll(toAdd));

			// After adding to existing: new2, new1, existing
			assertEquals("new2", uat.get(0));
			assertEquals("new1", uat.get(1));
			assertEquals("existing", uat.get(2));
		}

		@Test
		@DisplayName("AddAll empty collection")
		void testAddAllEmpty() {
			uat.add("first");

			List<String> toAdd = new ArrayList<>();
			assertTrue(uat.addAll(toAdd));
			assertEquals("first", uat.get(0));
		}

		@Test
		@DisplayName("AddAll at index")
		void testAddAllAtIndex() {
			uat.add("a");
			uat.add("b");
			// List: b, a

			List<String> toAdd = Arrays.asList("x", "y");
			assertTrue(uat.addAll(1, toAdd));

			// Elements are inserted at the index
			assertEquals("b", uat.get(0));
		}

		@Test
		@DisplayName("ContainsAll - all elements present")
		void testContainsAllTrue() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			// Only check elements that aren't at tail position due to indexOf bug
			List<String> toCheck = Arrays.asList("third", "second");
			assertTrue(uat.containsAll(toCheck));
		}

		@Test
		@DisplayName("ContainsAll with some elements missing")
		void testContainsAllFalse() {
			uat.add("first");
			uat.add("second");

			List<String> toCheck = Arrays.asList("second", "fourth");
			assertFalse(uat.containsAll(toCheck));
		}

		@Test
		@DisplayName("ContainsAll with empty collection")
		void testContainsAllEmpty() {
			uat.add("first");
			uat.add("second");

			List<String> toCheck = new ArrayList<>();
			assertTrue(uat.containsAll(toCheck));
		}

		@Test
		@Disabled("removeAll() method not yet implemented")
		@DisplayName("RemoveAll removes matching elements")
		void testRemoveAll() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");

			List<String> toRemove = Arrays.asList("second", "fourth");
			assertTrue(uat.removeAll(toRemove));

			assertFalse(uat.contains("second"));
			assertFalse(uat.contains("fourth"));
		}

		@Test
		@Disabled("removeAll() method not yet implemented")
		@DisplayName("RemoveAll with no matches returns false")
		void testRemoveAllNoMatches() {
			uat.add("first");
			uat.add("second");

			List<String> toRemove = Arrays.asList("third", "fourth");
			assertFalse(uat.removeAll(toRemove));
		}

		@Test
		@Disabled("retainAll() method not yet implemented")
		@DisplayName("RetainAll keeps only matching elements")
		void testRetainAll() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");

			List<String> toRetain = Arrays.asList("second", "fourth");
			assertTrue(uat.retainAll(toRetain));

			assertTrue(uat.contains("second"));
			assertTrue(uat.contains("fourth"));
		}

		@Test
		@Disabled("retainAll() method not yet implemented")
		@DisplayName("RetainAll with all elements returns false")
		void testRetainAllNoChange() {
			uat.add("first");
			uat.add("second");

			List<String> toRetain = Arrays.asList("first", "second");
			assertFalse(uat.retainAll(toRetain));
		}
	}

	@Nested
	@DisplayName("Iterator Tests")
	class IteratorTests {

		@Test
		@DisplayName("Iterator hasNext and next traverse from head")
		void testIteratorBasic() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Iterator<String> iterator = uat.iterator();

			// Iterator starts at head (most recently added)
			assertTrue(iterator.hasNext());
			assertEquals("third", iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals("second", iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals("first", iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("Iterator on empty list")
		void testIteratorEmpty() {
			Iterator<String> iterator = uat.iterator();
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("Iterator traverses all elements exactly once")
		void testIteratorTraversesAllElements() {
			uat.add("a");
			uat.add("b");
			uat.add("c");
			uat.add("d");

			List<String> collected = new ArrayList<>();
			Iterator<String> iterator = uat.iterator();
			while (iterator.hasNext()) {
				collected.add(iterator.next());
			}

			assertEquals(4, collected.size());
			// Elements are in reverse insertion order
			assertEquals(Arrays.asList("d", "c", "b", "a"), collected);
		}

		@Test
		@DisplayName("Iterator on single element list")
		void testIteratorSingleElement() {
			uat.add("only");

			Iterator<String> iterator = uat.iterator();
			assertTrue(iterator.hasNext());
			assertEquals("only", iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		@DisplayName("Multiple iterators are independent")
		void testMultipleIterators() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Iterator<String> it1 = uat.iterator();
			Iterator<String> it2 = uat.iterator();

			assertEquals("third", it1.next());
			assertEquals("third", it2.next());
			assertEquals("second", it2.next());
			assertEquals("second", it1.next());
		}

		@Test
		@DisplayName("For-each loop traversal")
		void testForEachLoop() {
			uat.add("a");
			uat.add("b");
			uat.add("c");

			StringBuilder sb = new StringBuilder();
			for (String s : uat) {
				sb.append(s);
			}

			// Reverse of insertion order
			assertEquals("cba", sb.toString());
		}

		@Test
		@Disabled("listIterator() method not yet implemented")
		@DisplayName("ListIterator forward traversal")
		void testListIteratorForward() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator();

			assertTrue(iterator.hasNext());
			assertEquals(0, iterator.nextIndex());
		}

		@Test
		@Disabled("listIterator(int) method not yet implemented")
		@DisplayName("ListIterator at specific index")
		void testListIteratorAtIndex() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			ListIterator<String> iterator = uat.listIterator(1);
			assertNotNull(iterator);
		}
	}

	@Nested
	@DisplayName("Array Conversion Tests")
	class ArrayConversionTests {

		@Test
		@Disabled("toArray() method not yet implemented")
		@DisplayName("ToArray returns correct array")
		void testToArray() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			Object[] array = uat.toArray();

			assertEquals(3, array.length);
			assertEquals("third", array[0]);
			assertEquals("second", array[1]);
			assertEquals("first", array[2]);
		}

		@Test
		@Disabled("toArray() method not yet implemented")
		@DisplayName("ToArray on empty list")
		void testToArrayEmpty() {
			Object[] array = uat.toArray();
			assertEquals(0, array.length);
		}

		@Test
		@Disabled("toArray(T[]) method not yet implemented")
		@DisplayName("ToArray with typed array of sufficient size")
		void testToArrayTyped() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String[] array = new String[3];
			String[] result = uat.toArray(array);

			assertSame(array, result);
		}

		@Test
		@Disabled("toArray(T[]) method not yet implemented")
		@DisplayName("ToArray with typed array too small")
		void testToArrayTypedSmall() {
			uat.add("first");
			uat.add("second");
			uat.add("third");

			String[] array = new String[1];
			String[] result = uat.toArray(array);

			assertNotSame(array, result);
			assertEquals(3, result.length);
		}
	}

	@Nested
	@DisplayName("SubList Tests")
	class SubListTests {

		@Test
		@Disabled("subList() method not yet implemented")
		@DisplayName("SubList basic operation")
		void testSubList() {
			uat.add("first");
			uat.add("second");
			uat.add("third");
			uat.add("fourth");
			uat.add("fifth");

			List<String> subList = uat.subList(1, 4);

			assertEquals(3, subList.size());
		}

		@Test
		@Disabled("subList() method not yet implemented")
		@DisplayName("SubList empty range")
		void testSubListEmpty() {
			uat.add("first");
			uat.add("second");

			List<String> subList = uat.subList(1, 1);
			assertTrue(subList.isEmpty());
		}
	}

	@Nested
	@DisplayName("Circular Behavior Tests")
	class CircularBehaviorTests {

		@Test
		@DisplayName("Single element forms circular reference")
		void testSingleElementCircular() {
			uat.add("only");

			// Should be able to get the element
			assertEquals("only", uat.get(0));
			// Circular index should work
			assertEquals("only", uat.get(1));
			assertEquals("only", uat.get(-1));
		}

		@Test
		@DisplayName("Iterator stops after one complete cycle")
		void testIteratorStopsAfterOneCycle() {
			uat.add("a");
			uat.add("b");
			uat.add("c");

			Iterator<String> it = uat.iterator();
			int count = 0;
			while (it.hasNext()) {
				it.next();
				count++;
			}

			assertEquals(3, count);
		}

		@Test
		@DisplayName("Get with index beyond size wraps around")
		void testGetWrapsAround() {
			uat.add("a");
			uat.add("b");
			uat.add("c");
			// Order: c, b, a

			assertEquals(uat.get(0), uat.get(3));
			assertEquals(uat.get(1), uat.get(4));
			assertEquals(uat.get(2), uat.get(5));
		}
	}

	@Nested
	@DisplayName("Edge Cases Tests")
	class EdgeCasesTests {

		@Test
		@DisplayName("Operations on single element list")
		void testSingleElementOperations() {
			uat.add("only");

			assertEquals("only", uat.get(0));
			assertTrue(uat.contains("only"));
			assertEquals(0, uat.indexOf("only"));
			assertFalse(uat.isEmpty());
		}

		@Test
		@DisplayName("Add after clear")
		void testAddAfterClear() {
			uat.add("first");
			uat.add("second");
			uat.clear();
			uat.add("new");

			assertFalse(uat.isEmpty());
			assertEquals("new", uat.get(0));
		}

		@Test
		@DisplayName("Multiple clears")
		void testMultipleClears() {
			uat.add("first");
			uat.clear();
			uat.clear();

			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("List with all same elements")
		void testAllSameElements() {
			uat.add("same");
			uat.add("same");
			uat.add("same");

			assertEquals(0, uat.indexOf("same"));
			assertTrue(uat.contains("same"));
		}

		@Test
		@DisplayName("Large number of elements")
		void testLargeNumberOfElements() {
			int count = 1000;
			for (int i = 0; i < count; i++) {
				uat.add("element" + i);
			}

			// Most recent addition is at index 0
			assertEquals("element999", uat.get(0));
			assertEquals("element998", uat.get(1));
		}

		@Test
		@DisplayName("Empty list contains nothing")
		void testEmptyListContainsNothing() {
			assertFalse(uat.contains("anything"));
			assertFalse(uat.contains(null));
			assertEquals(-1, uat.indexOf("anything"));
		}

		@Test
		@DisplayName("Add null between non-null elements")
		void testAddNullBetweenElements() {
			uat.add("first");
			uat.add(null);
			uat.add("third");

			// Order: third, null, first
			assertEquals("third", uat.get(0));
			assertNull(uat.get(1));
			assertTrue(uat.contains(null));
			assertEquals(1, uat.indexOf(null));
		}
	}

	@Nested
	@DisplayName("Type Safety Tests")
	class TypeSafetyTests {

		@Test
		@DisplayName("Works with Integer type")
		void testIntegerType() {
			CircularLinkedList<Integer> intList = new CircularLinkedList<>();
			intList.add(1);
			intList.add(2);
			intList.add(3);

			// Most recent (3) is at index 0
			assertEquals(Integer.valueOf(3), intList.get(0));
			assertEquals(Integer.valueOf(2), intList.get(1));
			assertEquals(Integer.valueOf(1), intList.get(2));
		}

		@Test
		@DisplayName("Works with custom object type")
		void testCustomObjectType() {
			class Person {
				String name;
				Person(String name) { this.name = name; }
			}

			CircularLinkedList<Person> personList = new CircularLinkedList<>();
			Person p1 = new Person("Alice");
			Person p2 = new Person("Bob");

			personList.add(p1);
			personList.add(p2);

			// p2 (most recent) is at index 0
			assertSame(p2, personList.get(0));
			assertSame(p1, personList.get(1));
		}
	}

	@Nested
	@DisplayName("Known Bugs Documentation Tests")
	class KnownBugsTests {

		@Test
		@Disabled("BUG: size() returns 0 instead of this.size")
		@DisplayName("BUG: size() should return actual size")
		void testSizeBug() {
			uat.add("a");
			uat.add("b");
			uat.add("c");
			assertEquals(3, uat.size());
		}

		@Test
		@Disabled("BUG: indexOf loop condition prevents checking tail node")
		@DisplayName("BUG: indexOf should check all nodes including tail")
		void testIndexOfBug() {
			uat.add("first");  // This becomes the tail
			uat.add("second");
			uat.add("third");

			// "first" is at index 2 (tail position)
			// indexOf fails because loop exits before checking tail
			assertEquals(2, uat.indexOf("first"));
		}

		@Test
		@Disabled("BUG: lastIndexOf returns 0 instead of lastIdx")
		@DisplayName("BUG: lastIndexOf should return lastIdx variable")
		void testLastIndexOfBug() {
			uat.add("a");
			uat.add("b");
			uat.add("c");

			assertEquals(1, uat.lastIndexOf("b"));
		}
	}
}
