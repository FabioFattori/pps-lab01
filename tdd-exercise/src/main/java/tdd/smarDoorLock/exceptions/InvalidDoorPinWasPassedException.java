package tdd.smarDoorLock.exceptions;

public class InvalidDoorPinWasPassedException extends IllegalArgumentException {
    public InvalidDoorPinWasPassedException(int passedPin) {
        super(
                "The passed pin (" + passedPin + " length:" + String.valueOf(passedPin).length() + ") is invalid, accepted pins are only four digits long"
        );
    }
}
