import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ForthEvaluator implements IForthEvaluator {
    private List<Integer> stackData = new ArrayList<>();
    private HashMap<String, Instruction> table = new HashMap<>();

    public ForthEvaluator() {
        table.put("+", new AddInstruction(this));
        table.put("-", new SubInstruction(this));
        table.put("*", new MulInstruction(this));
        table.put("/", new DivInstruction(this));
        table.put("dup", new DupInstruction(this));
        table.put("drop", new DropInstruction(this));
        table.put("swap", new SwapInstruction(this));
        table.put("over", new OverInstruction(this));
    }

    public List<Integer> evaluateProgram(List<String> program) {
        for (var instructions : program) {
            evaluateInstruction(instructions);
        }
        return stackData;
    }

    private void evaluateInstruction(String instructions) {
        if (instructions.startsWith(":")) {
            addSubroutine(instructions);
        } else {
            for (var instruction : instructions.split(" ")) {
                Instruction i = table.get(instruction.toLowerCase());
                if (i != null) {
                    i.execute();
                } else if (isNumber(instruction)) {
                    Instruction unSeenNumber = new PushInstruction(this, Integer.parseInt(instruction));
                    table.put(instruction, unSeenNumber);
                    unSeenNumber.execute();
                } else {
                    throw new IllegalArgumentException("No definition available for operator \"" + instruction + "\"");
                }
            }
        }
    }

    private void addSubroutine(String instructionToParse) {
        instructionToParse = instructionToParse.substring(2, instructionToParse.length() - 2);
        var instructions = instructionToParse.toLowerCase().split(" ");
        var subroutineName = instructions[0];

        if (isNumber(subroutineName)) {
            throw new IllegalArgumentException("Cannot redefine numbers");
        }

        List<Instruction> instr = new LinkedList<>();
        for (int i = 1; i < instructions.length; i++) {
            if (table.containsKey(instructions[i])) {
                instr.add(table.get(instructions[i]));
            } else if (isNumber(instructions[i])) {
                instr.add(new PushInstruction(this, Integer.parseInt(instructions[i])));
            }
        }

        table.put(subroutineName, new Instruction() {

            @Override
            public void execute() {
                for (Instruction instruction : instr) {
                    instruction.execute();
                }
            }

            @Override
            public String getName() {
                return "Subroutine";
            }

            @Override
            public int getNumberOfRequiredArgument() {
                return -1;
            }

        });

    }

    private boolean isNumber(String input) {
        return input.equals(input.replaceAll("[^0-9]", ""));
    }

    @Override
    public int stackSize() {
        return stackData.size();
    }

    @Override
    public int stackPeek() {
        return stackData.get(stackData.size() - 1);
    }

    @Override
    public int stackPop() {
        return stackData.remove(stackData.size() - 1);
    }

    @Override
    public void stackPush(int value) {
        stackData.add(value);
    }

}
