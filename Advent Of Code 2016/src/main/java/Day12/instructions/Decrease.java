package Day12.instructions;

import Day12.data.Register;

public class Decrease implements Instruction {

    private final Register destination;

    Decrease(Register destination) {
        this.destination = destination;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        destination.decreaseValue();
        instructionStack.moveToNextCommand();
    }
}