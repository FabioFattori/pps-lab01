package tdd.smarDoorLock.exceptions;

public class DoorCannotBeLockedWithoutPin extends IllegalStateException {
    public DoorCannotBeLockedWithoutPin() {
        super("A smart door lock cannot be locked without setting a pin first");
    }
}
