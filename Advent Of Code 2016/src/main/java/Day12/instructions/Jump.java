package Day12.instructions;

import Day12.data.DataHolder;
import Day12.data.Register;

public class Jump implements Instruction {

    private final DataHolder source;
    private final DataHolder distance;

    public Jump(DataHolder source, DataHolder distance) {
        this.source = source;
        this.distance = distance;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        if(source.getValue()==0) {
            instructionStack.moveToNextCommand();
            return;
        }
        instructionStack.jump(distance.getValue());
    }

    @Override
    public String toString() {
        return "JMP" + source + " " + distance;
    }
}