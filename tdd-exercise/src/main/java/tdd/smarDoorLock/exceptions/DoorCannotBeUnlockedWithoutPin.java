package tdd.smarDoorLock.exceptions;

public class DoorCannotBeUnlockedWithoutPin extends IllegalStateException {
    public DoorCannotBeUnlockedWithoutPin() {
        super("The door cannot be unlocked without setting the pin first");
    }
}
