package tdd.smarDoorLock.exceptions;

import tdd.smarDoorLock.enums.DoorState;

public class DoorPinCannotBeSetInCurrentState extends IllegalStateException {
    public DoorPinCannotBeSetInCurrentState(DoorState currentDoorState) {
        super("The door's pin can only be changed when the door is open, now it's in " + currentDoorState.name());
    }
}
