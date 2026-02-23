package tdd.circularQueue;

import tdd.circularQueue.exceptions.EmptyQueueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CircularQueueImpl implements CircularQueue {
    private final List<Integer> elements;
    private final int nElements;
    private int currentIndex;

    public CircularQueueImpl(int maximumPositionsOfQueue) {
        this.nElements = maximumPositionsOfQueue;
        this.elements = new ArrayList<>();
        this.currentIndex = -1;
        for (int i = 0; i < nElements; i++) {
            this.elements.add(null);
        }
    }

    private int getNextIndex() {
        final int possibleNextIndex = currentIndex + 1;
        if (possibleNextIndex >= nElements) {
            return 0;
        }

        return possibleNextIndex;
    }

    @Override
    public void add(int element) {
        currentIndex = getNextIndex();
        elements.set(currentIndex, element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public int getNextElementToBeRemoved() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.get(currentIndex);
    }

    @Override
    public boolean isEmpty() {
        return getNumberOfFilledPositions() == 0;
    }

    @Override
    public void removeNextElement() {
        final Object toRemove = getNextElementToBeRemoved();
        elements.remove(toRemove);
    }

    @Override
    public int getNextPositionValue() {
        return elements.get(getNextIndex());
    }

    @Override
    public int getNumberOfFilledPositions() {
        return elements.stream().filter(Objects::nonNull).toList().size();
    }
}
