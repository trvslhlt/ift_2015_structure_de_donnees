package data_structure.linked_list;
// mvn test -Dtest=LinkedPositionalListTest

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_structures.linked_list.LinkedPositionalList;
import data_structures.linked_list.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class LinkedPositionalListTest {

	private LinkedPositionalList<String> uat;

	@BeforeEach
	public void setUp() {
		this.uat = new LinkedPositionalList<>();
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
		@DisplayName("isEmpty returns false after adding element")
		void testIsEmptyReturnsFalseAfterAdd() {
			uat.addLast("first");
			assertFalse(uat.isEmpty());
		}

		@Test
		@DisplayName("Size increases when elements are added")
		void testSizeIncreasesWithAdd() {
			assertEquals(0, uat.size());
			uat.addLast("first");
			assertEquals(1, uat.size());
			uat.addLast("second");
			assertEquals(2, uat.size());
			uat.addLast("third");
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("Size decreases when elements are removed")
		void testSizeDecreasesWithRemove() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");
			assertEquals(3, uat.size());

			uat.remove(p2);
			assertEquals(2, uat.size());
			uat.remove(p1);
			assertEquals(1, uat.size());
			uat.remove(p3);
			assertEquals(0, uat.size());
			assertTrue(uat.isEmpty());
		}
	}

	@Nested
	@DisplayName("First and Last Position Tests")
	class FirstLastTests {

		@Test
		@DisplayName("first() returns null on empty list")
		void testFirstOnEmptyList() {
			assertNull(uat.first());
		}

		@Test
		@DisplayName("last() returns null on empty list")
		void testLastOnEmptyList() {
			assertNull(uat.last());
		}

		@Test
		@DisplayName("first() returns only element in single-element list")
		void testFirstOnSingleElementList() {
			Position<String> p = uat.addLast("only");
			assertEquals(p, uat.first());
			assertEquals("only", uat.first().getElement());
		}

		@Test
		@DisplayName("last() returns only element in single-element list")
		void testLastOnSingleElementList() {
			Position<String> p = uat.addLast("only");
			assertEquals(p, uat.last());
			assertEquals("only", uat.last().getElement());
		}

		@Test
		@DisplayName("first() and last() are same for single element")
		void testFirstAndLastSameForSingleElement() {
			Position<String> p = uat.addLast("only");
			assertEquals(uat.first(), uat.last());
		}

		@Test
		@DisplayName("first() returns first element in multi-element list")
		void testFirstOnMultiElementList() {
			Position<String> p1 = uat.addLast("first");
			uat.addLast("second");
			uat.addLast("third");
			assertEquals(p1, uat.first());
			assertEquals("first", uat.first().getElement());
		}

		@Test
		@DisplayName("last() returns last element in multi-element list")
		void testLastOnMultiElementList() {
			uat.addLast("first");
			uat.addLast("second");
			Position<String> p3 = uat.addLast("third");
			assertEquals(p3, uat.last());
			assertEquals("third", uat.last().getElement());
		}
	}

	@Nested
	@DisplayName("AddLast Tests")
	class AddLastTests {

		@Test
		@DisplayName("addLast to empty list")
		void testAddLastToEmptyList() {
			Position<String> p = uat.addLast("first");
			assertNotNull(p);
			assertEquals("first", p.getElement());
			assertEquals(1, uat.size());
			assertEquals(p, uat.first());
			assertEquals(p, uat.last());
		}

		@Test
		@DisplayName("addLast multiple elements maintains order")
		void testAddLastMultipleElements() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			assertEquals(3, uat.size());
			assertEquals(p1, uat.first());
			assertEquals(p3, uat.last());
			assertEquals("first", uat.first().getElement());
			assertEquals("third", uat.last().getElement());
		}

		@Test
		@DisplayName("addLast with null element")
		void testAddLastNullElement() {
			Position<String> p = uat.addLast(null);
			assertNotNull(p);
			assertNull(p.getElement());
			assertEquals(1, uat.size());
		}
	}

	@Nested
	@DisplayName("AddFirst Tests")
	class AddFirstTests {

		@Test
		@DisplayName("addFirst with element to empty list")
		void testAddFirstToEmptyList() {
			LinkedPositionalList<String> newList = new LinkedPositionalList<>();
			Position<String> p = newList.addFirst("dummy");
			assertNotNull(p);
		}
	}

	@Nested
	@DisplayName("Before and After Navigation Tests")
	class NavigationTests {

		@Test
		@DisplayName("before() on first element returns null")
		void testBeforeOnFirstElement() {
			Position<String> p1 = uat.addLast("first");
			uat.addLast("second");
			assertNull(uat.before(p1));
		}

		@Test
		@DisplayName("after() on last element returns null")
		void testAfterOnLastElement() {
			uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			assertNull(uat.after(p2));
		}

		@Test
		@DisplayName("before() returns previous position")
		void testBeforeReturnsCorrectPosition() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			assertEquals(p2, uat.before(p3));
			assertEquals(p1, uat.before(p2));
			assertEquals("second", uat.before(p3).getElement());
			assertEquals("first", uat.before(p2).getElement());
		}

		@Test
		@DisplayName("after() returns next position")
		void testAfterReturnsCorrectPosition() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			assertEquals(p2, uat.after(p1));
			assertEquals(p3, uat.after(p2));
			assertEquals("second", uat.after(p1).getElement());
			assertEquals("third", uat.after(p2).getElement());
		}

		@Test
		@DisplayName("before() throws exception for invalid position")
		void testBeforeThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.before(p));
		}

		@Test
		@DisplayName("after() throws exception for invalid position")
		void testAfterThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.after(p));
		}

		@Test
		@DisplayName("before() throws exception for null position")
		void testBeforeThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.before(null));
		}

		@Test
		@DisplayName("after() throws exception for null position")
		void testAfterThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.after(null));
		}
	}

	@Nested
	@DisplayName("AddBefore and AddAfter Tests")
	class AddBeforeAfterTests {

		@Test
		@DisplayName("addBefore adds element before given position")
		void testAddBefore() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p3 = uat.addLast("third");
			Position<String> p2 = uat.addBefore(p3, "second");

			assertEquals(3, uat.size());
			assertEquals("second", p2.getElement());
			assertEquals(p2, uat.after(p1));
			assertEquals(p2, uat.before(p3));
			assertEquals(p1, uat.before(p2));
			assertEquals(p3, uat.after(p2));
		}

		@Test
		@DisplayName("addBefore at first position")
		void testAddBeforeFirstPosition() {
			Position<String> p2 = uat.addLast("second");
			Position<String> p1 = uat.addBefore(p2, "first");

			assertEquals(2, uat.size());
			assertEquals(p1, uat.first());
			assertEquals("first", uat.first().getElement());
		}

		@Test
		@DisplayName("addBefore throws exception for null position")
		void testAddBeforeThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.addBefore(null, "element"));
		}

		@Test
		@DisplayName("addBefore throws exception for invalid position")
		void testAddBeforeThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.addBefore(p, "element"));
		}

		@Test
		@DisplayName("addAfter adds element after given position")
		void testAddAfter() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p3 = uat.addLast("third");
			Position<String> p2 = uat.addAfter(p1, "second");

			assertEquals(3, uat.size());
			assertEquals("second", p2.getElement());
			assertEquals(p2, uat.after(p1));
			assertEquals(p2, uat.before(p3));
		}

		@Test
		@DisplayName("addAfter at last position")
		void testAddAfterLastPosition() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addAfter(p1, "second");

			assertEquals(2, uat.size());
			assertEquals(p2, uat.last());
			assertEquals("second", uat.last().getElement());
		}

		@Test
		@DisplayName("addAfter throws exception for null position")
		void testAddAfterThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.addAfter(null, "element"));
		}

		@Test
		@DisplayName("addAfter throws exception for invalid position")
		void testAddAfterThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.addAfter(p, "element"));
		}

		@Test
		@DisplayName("addBefore with null element")
		void testAddBeforeNullElement() {
			Position<String> p = uat.addLast("first");
			Position<String> nullPos = uat.addBefore(p, null);
			assertNotNull(nullPos);
			assertNull(nullPos.getElement());
		}

		@Test
		@DisplayName("addAfter with null element")
		void testAddAfterNullElement() {
			Position<String> p = uat.addLast("first");
			Position<String> nullPos = uat.addAfter(p, null);
			assertNotNull(nullPos);
			assertNull(nullPos.getElement());
		}
	}

	@Nested
	@DisplayName("Set Tests")
	class SetTests {

		@Test
		@DisplayName("set replaces element and returns old value")
		void testSetReplacesElement() {
			Position<String> p = uat.addLast("old");
			String oldValue = uat.set(p, "new");

			assertEquals("old", oldValue);
			assertEquals("new", p.getElement());
			assertEquals(1, uat.size());
		}

		@Test
		@DisplayName("set on middle element")
		void testSetOnMiddleElement() {
			uat.addLast("first");
			Position<String> p2 = uat.addLast("old");
			uat.addLast("third");

			String oldValue = uat.set(p2, "new");

			assertEquals("old", oldValue);
			assertEquals("new", p2.getElement());
			assertEquals(3, uat.size());
		}

		@Test
		@DisplayName("set with null element")
		void testSetWithNullElement() {
			Position<String> p = uat.addLast("old");
			String oldValue = uat.set(p, null);

			assertEquals("old", oldValue);
			assertNull(p.getElement());
		}

		@Test
		@DisplayName("set throws exception for null position")
		void testSetThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.set(null, "element"));
		}

		@Test
		@DisplayName("set throws exception for invalid position")
		void testSetThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.set(p, "element"));
		}
	}

	@Nested
	@DisplayName("Remove Tests")
	class RemoveTests {

		@Test
		@DisplayName("remove returns element value")
		void testRemoveReturnsElement() {
			Position<String> p = uat.addLast("element");
			String removed = uat.remove(p);

			assertEquals("element", removed);
			assertEquals(0, uat.size());
			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("remove only element from list")
		void testRemoveOnlyElement() {
			Position<String> p = uat.addLast("only");
			uat.remove(p);

			assertTrue(uat.isEmpty());
			assertNull(uat.first());
			assertNull(uat.last());
		}

		@Test
		@DisplayName("remove first element")
		void testRemoveFirstElement() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.remove(p1);

			assertEquals(2, uat.size());
			assertEquals(p2, uat.first());
			assertEquals("second", uat.first().getElement());
		}

		@Test
		@DisplayName("remove last element")
		void testRemoveLastElement() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.remove(p3);

			assertEquals(2, uat.size());
			assertEquals(p2, uat.last());
			assertEquals("second", uat.last().getElement());
		}

		@Test
		@DisplayName("remove middle element")
		void testRemoveMiddleElement() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.remove(p2);

			assertEquals(2, uat.size());
			assertEquals(p3, uat.after(p1));
			assertEquals(p1, uat.before(p3));
		}

		@Test
		@DisplayName("remove throws exception for null position")
		void testRemoveThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.remove(null));
		}

		@Test
		@DisplayName("remove throws exception for invalid position")
		void testRemoveThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.remove(p));
		}

		@Test
		@DisplayName("remove all elements one by one")
		void testRemoveAllElements() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.remove(p1);
			assertEquals(2, uat.size());
			uat.remove(p2);
			assertEquals(1, uat.size());
			uat.remove(p3);
			assertEquals(0, uat.size());
			assertTrue(uat.isEmpty());
		}
	}

	@Nested
	@DisplayName("Move Operations Tests")
	class MoveOperationsTests {

		@Test
		@DisplayName("moveFirst moves element to front")
		void testMoveFirst() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.moveFirst(p3);

			assertEquals(3, uat.size());
			assertEquals(p3, uat.first());
			assertEquals("third", uat.first().getElement());
			assertEquals(p1, uat.after(p3));
		}

		@Test
		@DisplayName("moveFirst on already first element")
		void testMoveFirstOnFirstElement() {
			Position<String> p1 = uat.addLast("first");
			uat.addLast("second");

			uat.moveFirst(p1);

			assertEquals(2, uat.size());
			assertEquals(p1, uat.first());
		}

		@Test
		@DisplayName("moveFirst throws exception for null position")
		void testMoveFirstThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.moveFirst(null));
		}

		@Test
		@DisplayName("moveFirst throws exception for invalid position")
		void testMoveFirstThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.moveFirst(p));
		}

		@Test
		@DisplayName("moveLast moves element to end")
		void testMoveLast() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.moveLast(p1);

			assertEquals(3, uat.size());
			assertEquals(p1, uat.last());
			assertEquals("first", uat.last().getElement());
			assertEquals(p1, uat.after(p3));
		}

		@Test
		@DisplayName("moveLast on already last element")
		void testMoveLastOnLastElement() {
			uat.addLast("first");
			Position<String> p2 = uat.addLast("second");

			uat.moveLast(p2);

			assertEquals(2, uat.size());
			assertEquals(p2, uat.last());
		}

		@Test
		@DisplayName("moveLast throws exception for null position")
		void testMoveLastThrowsExceptionForNull() {
			assertThrows(IllegalArgumentException.class, () -> uat.moveLast(null));
		}

		@Test
		@DisplayName("moveLast throws exception for invalid position")
		void testMoveLastThrowsExceptionForInvalidPosition() {
			Position<String> p = uat.addLast("first");
			uat.remove(p);
			assertThrows(IllegalArgumentException.class, () -> uat.moveLast(p));
		}

		@Test
		@DisplayName("moveBefore moves element before target position")
		void testMoveBefore() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			Position<String> result = uat.moveBefore(p2, p3);

			assertEquals(3, uat.size());
			assertEquals(p3, result);
			assertEquals(p3, uat.after(p1));
			assertEquals(p2, uat.after(p3));
			assertEquals(p1, uat.before(p3));
			assertEquals(p3, uat.before(p2));
		}

		@Test
		@DisplayName("moveBefore to first position")
		void testMoveBeforeFirstPosition() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			uat.moveBefore(p1, p3);

			assertEquals(p3, uat.first());
			assertEquals(p1, uat.after(p3));
		}

		@Test
		@DisplayName("moveBefore throws exception for null target position")
		void testMoveBeforeThrowsExceptionForNullTarget() {
			Position<String> p = uat.addLast("first");
			assertThrows(IllegalArgumentException.class, () -> uat.moveBefore(null, p));
		}

		@Test
		@DisplayName("moveBefore throws exception for null position to move")
		void testMoveBeforeThrowsExceptionForNullToMove() {
			Position<String> p = uat.addLast("first");
			assertThrows(IllegalArgumentException.class, () -> uat.moveBefore(p, null));
		}

		@Test
		@DisplayName("moveBefore throws exception for invalid target position")
		void testMoveBeforeThrowsExceptionForInvalidTarget() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			uat.remove(p1);
			assertThrows(IllegalArgumentException.class, () -> uat.moveBefore(p1, p2));
		}

		@Test
		@DisplayName("moveBefore throws exception for invalid position to move")
		void testMoveBeforeThrowsExceptionForInvalidToMove() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			uat.remove(p2);
			assertThrows(IllegalArgumentException.class, () -> uat.moveBefore(p1, p2));
		}
	}

	@Nested
	@DisplayName("Iterator Tests")
	class IteratorTests {

		@Test
		@DisplayName("iterator on empty list")
		void testIteratorOnEmptyList() {
			Iterator<String> iter = uat.iterator();
			assertNotNull(iter);
			assertFalse(iter.hasNext());
		}

		@Test
		@DisplayName("iterator traverses all elements")
		void testIteratorTraversesAllElements() {
			uat.addLast("first");
			uat.addLast("second");
			uat.addLast("third");

			List<String> elements = new ArrayList<>();
			Iterator<String> iter = uat.iterator();

			while (iter.hasNext()) {
				elements.add(iter.next());
			}

			assertEquals(3, elements.size());
			assertEquals("first", elements.get(0));
			assertEquals("second", elements.get(1));
			assertEquals("third", elements.get(2));
		}

		@Test
		@DisplayName("iterator maintains order")
		void testIteratorMaintainsOrder() {
			uat.addLast("A");
			uat.addLast("B");
			uat.addLast("C");

			Iterator<String> iter = uat.iterator();
			assertEquals("A", iter.next());
			assertEquals("B", iter.next());
			assertEquals("C", iter.next());
			assertFalse(iter.hasNext());
		}

		@Test
		@DisplayName("iterator next() throws exception when no more elements")
		void testIteratorNextThrowsException() {
			uat.addLast("only");
			Iterator<String> iter = uat.iterator();
			iter.next();
			assertThrows(NoSuchElementException.class, () -> iter.next());
		}

		@Test
		@DisplayName("iterator works with null elements")
		void testIteratorWithNullElements() {
			uat.addLast("first");
			uat.addLast(null);
			uat.addLast("third");

			Iterator<String> iter = uat.iterator();
			assertEquals("first", iter.next());
			assertNull(iter.next());
			assertEquals("third", iter.next());
		}

		@Test
		@DisplayName("enhanced for-loop works")
		void testEnhancedForLoop() {
			uat.addLast("first");
			uat.addLast("second");
			uat.addLast("third");

			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			assertEquals(3, elements.size());
			assertEquals("first", elements.get(0));
			assertEquals("second", elements.get(1));
			assertEquals("third", elements.get(2));
		}
	}

	@Nested
	@DisplayName("Positions Iterable Tests")
	class PositionsTests {

		@Test
		@DisplayName("positions() returns iterable for empty list")
		void testPositionsOnEmptyList() {
			Iterable<Position<String>> positions = uat.positions();
			assertNotNull(positions);
			assertFalse(positions.iterator().hasNext());
		}

		@Test
		@DisplayName("positions() traverses all positions")
		void testPositionsTraversesAllPositions() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			List<Position<String>> positions = new ArrayList<>();
			for (Position<String> p : uat.positions()) {
				positions.add(p);
			}

			assertEquals(3, positions.size());
			assertEquals(p1, positions.get(0));
			assertEquals(p2, positions.get(1));
			assertEquals(p3, positions.get(2));
		}

		@Test
		@DisplayName("positions() maintains order")
		void testPositionsMaintainsOrder() {
			Position<String> p1 = uat.addLast("first");
			Position<String> p2 = uat.addLast("second");
			Position<String> p3 = uat.addLast("third");

			Iterator<Position<String>> iter = uat.positions().iterator();
			assertEquals(p1, iter.next());
			assertEquals(p2, iter.next());
			assertEquals(p3, iter.next());
			assertFalse(iter.hasNext());
		}

		@Test
		@DisplayName("positions() elements can be accessed")
		void testPositionsElementsCanBeAccessed() {
			uat.addLast("first");
			uat.addLast("second");
			uat.addLast("third");

			List<String> elements = new ArrayList<>();
			for (Position<String> p : uat.positions()) {
				elements.add(p.getElement());
			}

			assertEquals(3, elements.size());
			assertEquals("first", elements.get(0));
			assertEquals("second", elements.get(1));
			assertEquals("third", elements.get(2));
		}
	}

	@Nested
	@DisplayName("Sort Tests")
	class SortTests {

		@Test
		@DisplayName("sort empty list")
		void testSortEmptyList() {
			assertDoesNotThrow(() -> uat.sort());
			assertTrue(uat.isEmpty());
		}

		@Test
		@DisplayName("sort single element list")
		void testSortSingleElementList() {
			uat.addLast("only");
			uat.sort();
			assertEquals(1, uat.size());
			assertEquals("only", uat.first().getElement());
		}

		@Test
		@DisplayName("sort already sorted list")
		void testSortAlreadySortedList() {
			uat.addLast("A");
			uat.addLast("B");
			uat.addLast("C");

			uat.sort();

			assertEquals(3, uat.size());
			assertEquals("A", uat.first().getElement());
			assertEquals("C", uat.last().getElement());
		}

		@Test
		@DisplayName("sort reverse sorted list")
		void testSortReverseSortedList() {
			uat.addLast("C");
			uat.addLast("B");
			uat.addLast("A");

			uat.sort();

			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			assertEquals("A", elements.get(0));
			assertEquals("B", elements.get(1));
			assertEquals("C", elements.get(2));
		}

		@Test
		@DisplayName("sort unsorted list")
		void testSortUnsortedList() {
			uat.addLast("banana");
			uat.addLast("apple");
			uat.addLast("cherry");
			uat.addLast("date");

			uat.sort();

			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			assertEquals("apple", elements.get(0));
			assertEquals("banana", elements.get(1));
			assertEquals("cherry", elements.get(2));
			assertEquals("date", elements.get(3));
		}

		@Test
		@DisplayName("sort with duplicate elements")
		void testSortWithDuplicates() {
			uat.addLast("B");
			uat.addLast("A");
			uat.addLast("B");
			uat.addLast("A");

			uat.sort();

			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			assertEquals("A", elements.get(0));
			assertEquals("A", elements.get(1));
			assertEquals("B", elements.get(2));
			assertEquals("B", elements.get(3));
		}
	}

	@Nested
	@DisplayName("Integer Type Tests")
	class IntegerTypeTests {

		private LinkedPositionalList<Integer> intList;

		@BeforeEach
		void setUp() {
			intList = new LinkedPositionalList<>();
		}

		@Test
		@DisplayName("Works with Integer type")
		void testWithIntegerType() {
			intList.addLast(1);
			intList.addLast(2);
			intList.addLast(3);

			assertEquals(3, intList.size());
			assertEquals(1, intList.first().getElement());
			assertEquals(3, intList.last().getElement());
		}

		@Test
		@DisplayName("Sort works with Integer type")
		void testSortWithIntegerType() {
			intList.addLast(5);
			intList.addLast(2);
			intList.addLast(8);
			intList.addLast(1);

			intList.sort();

			List<Integer> elements = new ArrayList<>();
			for (Integer element : intList) {
				elements.add(element);
			}

			assertEquals(1, elements.get(0));
			assertEquals(2, elements.get(1));
			assertEquals(5, elements.get(2));
			assertEquals(8, elements.get(3));
		}
	}

	@Nested
	@DisplayName("Complex Scenario Tests")
	class ComplexScenarioTests {

		@Test
		@DisplayName("Mixed operations maintain list integrity")
		void testMixedOperations() {
			Position<String> p1 = uat.addLast("A");
			Position<String> p2 = uat.addLast("B");
			Position<String> p3 = uat.addAfter(p2, "C");
			Position<String> p4 = uat.addBefore(p1, "Z");

			assertEquals(4, uat.size());
			assertEquals("Z", uat.first().getElement());

			uat.remove(p2);
			assertEquals(3, uat.size());

			uat.set(p1, "A_modified");
			assertEquals("A_modified", p1.getElement());

			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			assertEquals(3, elements.size());
			assertEquals("Z", elements.get(0));
			assertEquals("A_modified", elements.get(1));
			assertEquals("C", elements.get(2));
		}

		@Test
		@DisplayName("Multiple move operations")
		void testMultipleMoveOperations() {
			Position<String> p1 = uat.addLast("1");
			Position<String> p2 = uat.addLast("2");
			Position<String> p3 = uat.addLast("3");
			Position<String> p4 = uat.addLast("4");

			uat.moveLast(p1);
			assertEquals("1", uat.last().getElement());

			uat.moveFirst(p4);
			assertEquals("4", uat.first().getElement());

			uat.moveBefore(p2, p3);
			assertEquals(p3, uat.after(p4));
		}

		@Test
		@DisplayName("Build, modify, and verify list")
		void testBuildModifyVerify() {
			// Build
			for (int i = 0; i < 5; i++) {
				uat.addLast("Item" + i);
			}
			assertEquals(5, uat.size());

			// Modify
			Position<String> current = uat.first();
			while (current != null) {
				uat.set(current, current.getElement() + "_modified");
				current = uat.after(current);
			}

			// Verify
			List<String> elements = new ArrayList<>();
			for (String element : uat) {
				elements.add(element);
			}

			for (int i = 0; i < 5; i++) {
				assertTrue(elements.get(i).endsWith("_modified"));
			}
		}
	}
}
