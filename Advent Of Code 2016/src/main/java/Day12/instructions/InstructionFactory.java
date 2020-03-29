package Day12.instructions;

import Day12.data.DataHolder;
import Day12.data.Number;
import Day12.data.Register;
import Day12.data.Registers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {

    private final Pattern CPY_INSTRUCTION = Pattern.compile("cpy (-?\\d+|[a-z]) ([a-z])");
    private final Pattern INC_INSTRUCTION = Pattern.compile("inc ([a-z])");
    private final Pattern DEC_INSTRUCTION = Pattern.compile("dec ([a-z])");
    private final Pattern JNZ_INSTRUCTION = Pattern.compile("jnz (-?\\d+|[a-z]) (-?\\d+)");

    public Instruction parse(Registers registers, String instruction) {
        Matcher matcher = CPY_INSTRUCTION.matcher(instruction);
        if(matcher.matches()) {
            DataHolder source = getDataHolder(registers, matcher.group(1));
            Register destination = registers.getRegister(matcher.group(2));
            return new Copy(source, destination);
        }
        matcher = INC_INSTRUCTION.matcher(instruction);
        if(matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new Increase(destination);
        }
        matcher = DEC_INSTRUCTION.matcher(instruction);
        if(matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new Decrease(destination);
        }
        matcher = JNZ_INSTRUCTION.matcher(instruction);
        if(matcher.matches()) {
            return new Jump(
                    getDataHolder(registers, matcher.group(1)),
                    getDataHolder(registers, matcher.group(2))
            );
        }
        throw new IllegalArgumentException("Can not parse + [" + instruction + "]");
    }

    private static DataHolder getDataHolder(Registers registers,String dataHolderName) {
        try {
            return new Number(Integer.valueOf(dataHolderName));
        } catch (NumberFormatException e) {
            return registers.getRegister(dataHolderName);
        }
    }
}