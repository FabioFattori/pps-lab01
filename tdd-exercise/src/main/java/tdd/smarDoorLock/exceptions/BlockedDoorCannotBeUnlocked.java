package tdd.smarDoorLock.exceptions;

public class BlockedDoorCannotBeUnlocked extends IllegalStateException{
    public BlockedDoorCannotBeUnlocked(){
        super("The door is currently blocked and cannot be unlocked manually, please reset it first");
    }
}
