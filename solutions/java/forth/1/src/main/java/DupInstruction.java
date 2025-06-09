import java.util.List;

public class DupInstruction implements Instruction {

    private ForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;

    @Override
    public void execute() {
        List<Integer> stackData = fe.getStackData();
        if (stackData.size() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = stackData.get(stackData.size() - 1);
        stackData.add(a);
    }

    public DupInstruction(ForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getSymbol() {
        return "dup";
    }

    @Override
    public String getName() {
        return "Duplicating";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
