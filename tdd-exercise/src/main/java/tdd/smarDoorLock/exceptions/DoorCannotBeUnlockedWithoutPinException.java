package tdd.smarDoorLock.exceptions;

public class DoorCannotBeUnlockedWithoutPinException extends IllegalStateException {
    public DoorCannotBeUnlockedWithoutPinException() {
        super("The door cannot be unlocked without setting the pin first");
    }
}
