public class DropInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;

    @Override
    public void execute() {
        if (fe.stackSize() < NUM_REQUIRED_ARGUMENT) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        fe.stackPop();
    }

    public DropInstruction(IForthEvaluator fe) {
        this.fe = fe;
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
