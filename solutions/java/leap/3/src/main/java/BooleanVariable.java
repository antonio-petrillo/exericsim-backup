import java.util.Map;

public class BooleanVariable implements BooleanExpression {
	private String label;

	public BooleanVariable(String label) {
		this.label = label;
	}

	@Override
	public boolean eval(Map<? extends BooleanExpression, Boolean> table) {
		if(!table.containsKey(this)) {
			throw new IllegalArgumentException("label not found");
		}
		return table.get(this);
	}

	@Override
	public String toString() {
		return label;
	}
}
