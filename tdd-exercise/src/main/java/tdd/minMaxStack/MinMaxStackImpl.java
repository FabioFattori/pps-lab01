package tdd.minMaxStack;

import tdd.minMaxStack.exceptions.EmptyMinMaxStackExceptions;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    private final Stack<Integer> baseStack;
    private final Stack<Integer> maxStack;
    private final Stack<Integer> minStack;

    public MinMaxStackImpl() {
        this.baseStack = new Stack<>();
        this.maxStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    @Override
    public void push(int value) {
        baseStack.push(value);

        if (minStack.isEmpty() || minStack.peek() >= value) {
            minStack.push(value);
        }

        if (maxStack.isEmpty() || maxStack.peek() <= value) {
            maxStack.push(value);
        }
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyMinMaxStackExceptions();
        }
        final int poppedValue = baseStack.pop();

        if (minStack.peek() == poppedValue) {
            minStack.pop();
        } else if (maxStack.peek() == poppedValue) {
            maxStack.pop();
        }

        return poppedValue;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new EmptyMinMaxStackExceptions();
        }

        return baseStack.peek();
    }

    @Override
    public int getMin() {
        return minStack.peek();
    }

    @Override
    public int getMax() {
        return maxStack.peek();
    }

    @Override
    public boolean isEmpty() {
        return baseStack.isEmpty();
    }

    @Override
    public int size() {
        return baseStack.size();
    }
}
