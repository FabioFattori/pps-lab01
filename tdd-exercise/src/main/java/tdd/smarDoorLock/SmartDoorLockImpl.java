package tdd.smarDoorLock;

import tdd.smarDoorLock.enums.DoorState;
import tdd.smarDoorLock.exceptions.*;

public class SmartDoorLockImpl implements SmartDoorLock {
    private Integer pin;
    private DoorState currentDoorState;
    private int failedAttempts;

    public SmartDoorLockImpl() {
        this.reset();
    }

    private boolean isPinValid(int pin) {
        int pinLength = 4;

        return String.valueOf(pin).length() == pinLength;
    }

    @Override
    public void setPin(int pin) {
        if (!isPinValid(pin)) {
            throw new InvalidDoorPinWasPassedException(pin);
        }

        if (!isUnlocked()) {
            throw new DoorPinCannotBeSetInCurrentStateException(currentDoorState);
        }

        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (isBlocked()) {
            throw new BlockedDoorCannotBeUnlockedException();
        }

        if (this.pin == null) {
            throw new DoorCannotBeUnlockedWithoutPinException();
        }

        if (this.pin == pin) {
            currentDoorState = DoorState.unlocked;
            failedAttempts = 0;
            return;
        }

        handleFailedAttempt();
    }

    private void handleFailedAttempt() {
        failedAttempts++;
        if (getFailedAttempts() >= getMaxAttempts()) {
            currentDoorState = DoorState.blocked;
        }
    }

    @Override
    public void lock() throws DoorCannotBeLockedWithoutPinException {
        if (pin == null) {
            throw new DoorCannotBeLockedWithoutPinException();
        }
        currentDoorState = DoorState.locked;
    }

    @Override
    public boolean isLocked() {
        return currentDoorState == DoorState.locked;
    }

    @Override
    public boolean isUnlocked() {
        return currentDoorState == DoorState.unlocked;
    }

    @Override
    public boolean isBlocked() {
        return currentDoorState == DoorState.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return 5;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void reset() {
        pin = null;
        currentDoorState = DoorState.unlocked;
        failedAttempts = 0;
    }
}
