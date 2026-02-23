package tdd.circularQueue.exceptions;

public class EmptyQueueException extends IllegalStateException {
    public EmptyQueueException() {
        super("The queue is empty");
    }
}
