package tdd.smarDoorLock.exceptions;

public class BlockedDoorCannotBeUnlockedException extends IllegalStateException{
    public BlockedDoorCannotBeUnlockedException(){
        super("The door is currently blocked and cannot be unlocked manually, please reset it first");
    }
}
