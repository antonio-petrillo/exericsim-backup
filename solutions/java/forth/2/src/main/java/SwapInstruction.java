public class SwapInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 2;

    @Override
    public void execute() {
        if (fe.stackSize() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = fe.stackPop();
        int b = fe.stackPop();
        fe.stackPush(a);
        fe.stackPush(b);
    }

    public SwapInstruction(IForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getName() {
        return "Swapping";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
