import java.util.List;

public class PushInstruction implements Instruction {

    private ForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;
    private int value;

    @Override
    public void execute() {
        List<Integer> stackData = fe.getStackData();
        // if (stackData.size() < NUM_REQUIRED_ARGUMENT) {
        // throw new IllegalArgumentException(getErrorMessage());
        // }
        stackData.add(value);
    }

    public PushInstruction(ForthEvaluator fe, int value) {
        this.fe = fe;
        this.value = value;
    }

    @Override
    public String getSymbol() {
        return "push";
    }

    @Override
    public String getName() {
        return "Pushing";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
