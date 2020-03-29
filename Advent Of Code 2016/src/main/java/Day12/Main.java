package Day12;

import Day12.data.DataReader;
import Day12.data.Registers;
import Day12.instructions.Instruction;
import Day12.instructions.InstructionFactory;
import Day12.instructions.InstructionStack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String INPUT_PATH = "C:\\Users\\Admin\\IdeaProjects\\Java\\Advent Of Code 2016\\src\\main\\resources\\assembunny.txt";

//    Main() {
//        DataReader dataReader = new DataReader(INPUT_PATH);
//        List<String> commandList = dataReader.getAsList();
//
//        InstructionFactory instructionFactory = new InstructionFactory();
//        Registers registers = new Registers();
//        List<Instruction> instructionList = new ArrayList<>();
//
//        for(int i=0;i<commandList.size();i++) {
//            instructionList.add(instructionFactory.parse(registers, commandList.get(i)));
//        }
//
//        InstructionStack instructionStack = new InstructionStack(instructionList);
//        instructionStack.execute();
//
//        System.out.println("Value of register 'a': " + registers.getRegister("a").getValue());
//
//    }

    public static void main(String[] args) {
        //new Main();
        DataReader dataReader = new DataReader(INPUT_PATH);
        new Computer(dataReader.getAsList());
    }
}