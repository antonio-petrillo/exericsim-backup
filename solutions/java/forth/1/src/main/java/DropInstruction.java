import java.util.List;

public class DropInstruction implements Instruction {

    private ForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;

    @Override
    public void execute() {
        List<Integer> stackData = fe.getStackData();
        if (stackData.size() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = stackData.get(stackData.size() - 1);
        stackData.remove(stackData.size() - 1);
    }

    public DropInstruction(ForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getSymbol() {
        return "drop";
    }

    @Override
    public String getName() {
        return "Dropping";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
