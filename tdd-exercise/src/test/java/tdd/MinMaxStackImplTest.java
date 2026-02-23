package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.minMaxStack.MinMaxStack;
import tdd.minMaxStack.MinMaxStackImpl;
import tdd.minMaxStack.exceptions.EmptyMinMaxStackExceptions;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStack stack;
    private static final int GENERIC_ELEMENT = 5;

    @BeforeEach
    public void beforeEach() {
        stack = new MinMaxStackImpl();
    }

    private int pushAndPopGenericElement() {
        stack.push(GENERIC_ELEMENT);
        return stack.pop();
    }

    private void doAGivenNumberOfPush(int maxNumberPushedInStack) {
        final int nCycles = maxNumberPushedInStack + 1;
        for (int element = 0; element < nCycles; element++) {
            stack.push(element);
        }
    }

    @Test
    public void testPopWithEmptyStackReturnsAnError() {
        assertThrows(EmptyMinMaxStackExceptions.class, () -> stack.pop());
    }

    @Test
    public void testPeekWithEmptyStackReturnsAnError() {
        assertThrows(EmptyMinMaxStackExceptions.class, () -> stack.peek());
    }

    @Test
    public void testPushedElementIsPopped() {
        assertEquals(GENERIC_ELEMENT, pushAndPopGenericElement());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushedElementIsPeekedAndNotPopped() {
        stack.push(GENERIC_ELEMENT);
        assertEquals(GENERIC_ELEMENT, stack.peek());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testSizeReturnsRealStackSizeAfterPush() {
        final int expectedSize = 1;
        stack.push(GENERIC_ELEMENT);
        assertEquals(expectedSize, stack.size());
    }

    @Test
    public void testSizeReturnsRealStackSizeAfterPushAndPop() {
        final int expectedSize = 0;
        pushAndPopGenericElement();
        assertEquals(expectedSize, stack.size());
    }

    @Test
    public void testGetMinReturnsRealMinAfterSomePush() {
        final int numberOfPush = 5;
        final int expectedMin = 0;
        doAGivenNumberOfPush(numberOfPush);
        assertEquals(expectedMin, stack.getMin());
    }

    @Test
    void testGetMaxReturnsRealMaxAfterSomePush() {
        final int expectedMax = 4;
        doAGivenNumberOfPush(expectedMax);
        assertEquals(expectedMax, stack.getMax());
    }

    @Test
    public void testAfterAPopOfTheCurrentMinTheNewMinIsTheNextMinimumValueOfTheStack() {
        final int expectedMin = 0;
        final int toBePoppedMin = -1;
        stack.push(expectedMin);
        stack.push(toBePoppedMin);
        stack.pop();
        assertEquals(expectedMin, stack.getMin());
    }

    @Test
    public void testAfterAPopOfTheCurrentMaxTheNewMaxIsTheNextMaximumValueOfTheStack() {
        final int firstMax = 10;
        final int expectedMax = firstMax - 1;
        doAGivenNumberOfPush(firstMax);
        stack.pop();
        assertEquals(expectedMax, stack.getMax());
    }
}