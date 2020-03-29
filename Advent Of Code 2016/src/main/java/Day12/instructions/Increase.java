package Day12.instructions;

import Day12.data.Register;

public class Increase implements Instruction {

    private final Register destination;

    Increase(Register destination) {
        this.destination = destination;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        destination.increaseValue();
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "INC " + destination;
    }
}
