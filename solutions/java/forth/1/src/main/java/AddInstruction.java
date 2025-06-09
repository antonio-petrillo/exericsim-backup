import java.util.List;

public class AddInstruction implements Instruction {

    private ForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 2;

    @Override
    public void execute() {
        List<Integer> stackData = fe.getStackData();
        if (stackData.size() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = stackData.remove(stackData.size() - 1);
        int b = stackData.remove(stackData.size() - 1);
        stackData.add(a + b);
    }

    public AddInstruction(ForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public String getName() {
        return "Addition";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
