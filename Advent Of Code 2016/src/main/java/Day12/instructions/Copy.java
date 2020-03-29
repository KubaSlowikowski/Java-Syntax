package Day12.instructions;

import Day12.data.DataHolder;
import Day12.data.Register;

public class Copy implements Instruction {

    private final DataHolder source;
    private final Register destination;

    Copy(DataHolder source , Register destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        destination.setValue(source.getValue());
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "CPY " + source + destination;
    }
}