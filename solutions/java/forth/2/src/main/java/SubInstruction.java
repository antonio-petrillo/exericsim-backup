public class SubInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 2;

    @Override
    public void execute() {
        if (fe.stackSize() < 2) {
            throw new IllegalArgumentException(getErrorMessage());
        }
        int a = fe.stackPop();
        int b = fe.stackPop();
        fe.stackPush(b - a);
    }

    public SubInstruction(IForthEvaluator fe) {
        this.fe = fe;
    }

    @Override
    public String getName() {
        return "Subtraction";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
