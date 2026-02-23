package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.circularQueue.CircularQueue;
import tdd.circularQueue.CircularQueueImpl;
import tdd.circularQueue.exceptions.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private static final int GENERIC_ELEMENT = 5;
    private static final int MAXIMUM_POSITIONS_OF_QUEUE = 5;
    private CircularQueue queue;

    @BeforeEach
    public void beforeEach() {
        queue = new CircularQueueImpl(MAXIMUM_POSITIONS_OF_QUEUE);
    }

    public void addNElementsToTheQueue(int nElementsToAdd) {
        for (int i = 0; i < nElementsToAdd + 1; i++) {
            queue.add(i);
        }
    }

    @Test
    public void testAddAnElement() {
        final int expectedSize = 1;
        queue.add(GENERIC_ELEMENT);
        assertEquals(expectedSize, queue.getNumberOfFilledPositions());
        assertEquals(GENERIC_ELEMENT, queue.getNextElementToBeRemoved());
    }

    @Test
    public void testCannotGetNextElementToBeRemovedWithEmptyQueue() {
        assertThrows(EmptyQueueException.class, () -> queue.getNextElementToBeRemoved());
    }

    @Test
    public void testRemoveNextElement() {
        final int expectedSize = 1;
        addNElementsToTheQueue(expectedSize);
        queue.removeNextElement();
        assertEquals(expectedSize, queue.getNumberOfFilledPositions());
    }

    @Test
    public void testOverflowAddDoesNotChangeTheActualSizeOfTheQueue() {
        addNElementsToTheQueue(MAXIMUM_POSITIONS_OF_QUEUE * 2);

        assertEquals(MAXIMUM_POSITIONS_OF_QUEUE, queue.size());
    }

    @Test
    public void testOverflowAddedValuesAreInsertedInACircularWay() {
        final int expectedValue = 1;
        addNElementsToTheQueue(MAXIMUM_POSITIONS_OF_QUEUE);

        assertEquals(expectedValue, queue.getNextPositionValue());
    }
}
