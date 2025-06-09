public class PushInstruction implements Instruction {

    private IForthEvaluator fe;
    private final int NUM_REQUIRED_ARGUMENT = 1;
    private int value;

    @Override
    public void execute() {
        fe.stackPush(value);
    }

    public PushInstruction(IForthEvaluator fe, int value) {
        this.fe = fe;
        this.value = value;
    }

    @Override
    public String getName() {
        return "Pushing";
    }

    @Override
    public int getNumberOfRequiredArgument() {
        return NUM_REQUIRED_ARGUMENT;
    }

}
