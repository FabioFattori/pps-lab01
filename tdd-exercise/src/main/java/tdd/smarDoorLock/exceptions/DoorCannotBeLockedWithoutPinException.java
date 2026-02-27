package tdd.smarDoorLock.exceptions;

public class DoorCannotBeLockedWithoutPinException extends IllegalStateException {
    public DoorCannotBeLockedWithoutPinException() {
        super("A smart door lock cannot be locked without setting a pin first");
    }
}
