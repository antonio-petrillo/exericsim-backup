public class DupInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;

    @Override
    public void execute() {
        if (fe.stackSize() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = fe.stackPeek();
        fe.stackPush(a);
    }

    public DupInstruction(IForthEvaluator fe) {
        this.fe = fe;
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
