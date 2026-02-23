package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.smarDoorLock.exceptions.*;
import tdd.smarDoorLock.SmartDoorLock;
import tdd.smarDoorLock.SmartDoorLockImpl;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLock;
    private static final int CORRECT_CODE = 9823;
    private static final int WRONG_CODE = 5000;

    @BeforeEach
    void beforeEach() {
        smartDoorLock = new SmartDoorLockImpl();
    }

    private void lockDoorWithPin() {
        smartDoorLock.setPin(CORRECT_CODE);
        smartDoorLock.lock();
    }

    private void blockDoorWithDefaultPin() {
        lockDoorWithPin();
        for (int i = 0; i < smartDoorLock.getMaxAttempts(); i++) {
            smartDoorLock.unlock(WRONG_CODE);
        }
    }

    @Test
    public void testWithoutPinTheDoorCannotBeLocked() {
        assertThrows(DoorCannotBeLockedWithoutPin.class, () -> smartDoorLock.lock());
    }

    @Test
    public void testAtInitializationTheDoorIsUnlocked() {
        assertTrue(smartDoorLock.isUnlocked());
    }

    @Test
    public void testWithoutPinTheDoorCannotBeUnlocked() {
        assertThrows(DoorCannotBeUnlockedWithoutPin.class, () -> smartDoorLock.unlock(CORRECT_CODE));
    }

    @Test
    public void testAPinCanBeSet() {
        assertDoesNotThrow(this::lockDoorWithPin);
    }

    @Test
    public void testAPinCanOnlyBeSetWhenDoorIsUnlocked() {
        lockDoorWithPin();
        assertThrows(DoorPinCannotBeSetInCurrentState.class, () -> smartDoorLock.setPin(CORRECT_CODE));
    }

    @Test
    public void testAcceptedPinAreCodesOfFourDigitsLong() {
        final int threeDigitsLongCode = 982;
        final int fiveDigitsLongCode = 98123;
        assertThrows(InvalidDoorPinWasPassed.class, () -> smartDoorLock.setPin(threeDigitsLongCode));
        assertThrows(InvalidDoorPinWasPassed.class, () -> smartDoorLock.setPin(fiveDigitsLongCode));
    }

    @Test
    public void testTheDoorCanBeUnlocked() {
        lockDoorWithPin();
        smartDoorLock.unlock(CORRECT_CODE);
        assertTrue(smartDoorLock.isUnlocked());
    }

    @Test
    public void testFailedAttemptsGetIncreasedUponFailing() {
        final int expectedNumberOfFailedAttempts = 1;
        lockDoorWithPin();
        smartDoorLock.unlock(WRONG_CODE);
        assertEquals(expectedNumberOfFailedAttempts, smartDoorLock.getFailedAttempts());
    }

    @Test
    public void testDoorGetsBlockedUponReachingMaxNumberOfAttempts() {
        blockDoorWithDefaultPin();
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    public void testBlockedDoorCanBeResetAndUnlocked() {
        blockDoorWithDefaultPin();
        smartDoorLock.reset();
        testTheDoorCanBeUnlocked();
    }

    @Test
    public void testBlockedDoorCannotBeUnlockedManually(){
        blockDoorWithDefaultPin();
        assertThrows(BlockedDoorCannotBeUnlocked.class, () -> smartDoorLock.unlock(CORRECT_CODE));
    }

    @Test
    public void testUponSuccessfullyUnlockFailedAttemptsGetReset(){
        final int zeroAttemptsFailed = 0;
        lockDoorWithPin();
        smartDoorLock.unlock(WRONG_CODE);
        smartDoorLock.unlock(WRONG_CODE);
        smartDoorLock.unlock(CORRECT_CODE);
        assertEquals(zeroAttemptsFailed, smartDoorLock.getFailedAttempts());
    }
}
