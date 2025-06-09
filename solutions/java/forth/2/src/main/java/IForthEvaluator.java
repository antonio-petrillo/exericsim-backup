import java.util.List;

public interface IForthEvaluator {
    public List<Integer> evaluateProgram(List<String> program);

    public int stackSize();

    public int stackPeek();

    public int stackPop();

    public void stackPush(int value);
}
