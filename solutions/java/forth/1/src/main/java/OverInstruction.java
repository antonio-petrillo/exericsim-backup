import java.util.List;

public class OverInstruction implements Instruction {

    private ForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 2;

    @Override
    public void execute() {
        List<Integer> stackData = fe.getStackData();
        if (stackData.size() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = stackData.get(stackData.size() - 2);
        stackData.add(a);
    }

    @Override
    public String getSymbol() {
        return "over";
    }

    public OverInstruction(ForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getName() {
        return "Overing";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
