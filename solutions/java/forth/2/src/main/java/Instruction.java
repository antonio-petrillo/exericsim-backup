public interface Instruction {
    void execute();

    int getNumberOfRequiredArgument();

    String getName();

    default String getErrorMessage() {
        int num = getNumberOfRequiredArgument();
        String endMsg = num > 1 ? num + " values" : num + " value";
        String msg = String.format("%s requires that the stack contain at least %s", getName(), endMsg);
        return msg;
    }
}
