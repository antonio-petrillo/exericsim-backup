import java.util.List;

public class DivInstruction implements Instruction {

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
        if (a == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
        stackData.add(b / a);
    }

    public DivInstruction(ForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public String getName() {
        return "Division";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
