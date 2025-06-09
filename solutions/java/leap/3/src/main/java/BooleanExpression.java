import java.util.Map;

public interface BooleanExpression {
	public boolean eval(Map<? extends BooleanExpression, Boolean> table);
}
