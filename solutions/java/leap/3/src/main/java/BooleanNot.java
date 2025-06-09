import java.util.Map;

public class BooleanNot implements BooleanExpression {
	private BooleanExpression expression;

	public BooleanNot(BooleanExpression expression) {
		this.expression = expression;
	}

	@Override
	public boolean eval(Map<? extends BooleanExpression, Boolean> table) {
		return ! expression.eval(table);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(!");
		sb.append(expression.toString());
		sb.append(")");
		return sb.toString();
	}

}
