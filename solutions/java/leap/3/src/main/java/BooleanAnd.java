import java.util.Map;

public class BooleanAnd implements BooleanExpression {

	private BooleanExpression x, y;

	public BooleanAnd(BooleanExpression x, BooleanExpression y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean eval(Map<? extends BooleanExpression, Boolean> table) {
		return x.eval(table) && y.eval(table);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(x.toString());
		sb.append(" && ");
		sb.append(y.toString());
		sb.append(")");
		return sb.toString();
	}
}
