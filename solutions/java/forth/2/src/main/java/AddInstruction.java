public class AddInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 2;

    @Override
    public void execute() {
        if (fe.stackSize() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = fe.stackPop();
        int b = fe.stackPop();
        fe.stackPush(a + b);
    }

    public AddInstruction(IForthEvaluator fe) {
        this.fe = fe;
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
