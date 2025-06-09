import java.util.Map;
import java.util.HashMap;

public class Leap {

    public boolean isLeapYear(int year) {

		BooleanVariable divisibleBy4 = new BooleanVariable("x");
		BooleanVariable divisibleBy100 = new BooleanVariable("y");
		BooleanVariable divisibleBy400 = new BooleanVariable("z");

		Map<BooleanVariable, Boolean> table = new HashMap<BooleanVariable, Boolean>();

		table.put(divisibleBy4, year % 4 == 0);
		table.put(divisibleBy400, year % 400 == 0);
		table.put(divisibleBy100, year % 100 == 0);

		BooleanNot notDivisibleBy100 = new BooleanNot(divisibleBy100);
		BooleanOr divisibleBy400OrNotBy100 = new BooleanOr(divisibleBy400, notDivisibleBy100);
		BooleanAnd leap = new BooleanAnd(divisibleBy4, divisibleBy400OrNotBy100);

        return leap.eval(table);
    }

}
