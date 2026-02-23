package tdd.smarDoorLock.exceptions;

public class InvalidDoorPinWasPassed extends IllegalArgumentException {
    public InvalidDoorPinWasPassed(int passedPin) {
        super(
                "The passed pin (" + passedPin + " length:" + String.valueOf(passedPin).length() + ") is invalid, accepted pins are only four digits long"
        );
    }
}
