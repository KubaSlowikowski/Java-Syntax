package Day12.data;

import Day12.data.Register;

import java.util.HashMap;
import java.util.Map;

public class Registers {

    private Map<String, Register> registers = new HashMap<>();

    public Register getRegister(String name) {
        if(!registers.containsKey(name))
            registers.put(name, new Register(name));
        return registers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Register r : registers.values()) {
            sb.append(r);
            sb.append(" ");
        }
        return sb.toString();
    }
}