package Day12;

import Day12.data.Register;
import Day12.data.Registers;
import Day12.instructions.Instruction;
import Day12.instructions.InstructionFactory;
import Day12.instructions.InstructionStack;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private final InstructionStack instructionStack;
    private final Registers registers;

    public Computer(List<String> commandList) {
        this(commandList, new Registers());
    }

    public Computer(List<String> commandList, Registers registers) {
        registers.getRegister("c").setValue(1);
        this.registers = registers;
        instructionStack = instantiateInstructions(commandList);
        instructionStack.execute();
        System.out.println("Value of register 'a': " + registers.getRegister("a").getValue());
    }

    private InstructionStack instantiateInstructions(List<String> instructionsToParse) {
        List<Instruction> instructions = new ArrayList<>();
        InstructionFactory instructionFactory = new InstructionFactory();
        for(String instruction : instructionsToParse) {
            instructions.add(instructionFactory.parse(registers, instruction));
        }
        return new InstructionStack<>(instructions);
    }
}