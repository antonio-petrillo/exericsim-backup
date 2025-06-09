import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChangeCalculator {

    private List<Integer> coins;

    public ChangeCalculator(List<Integer> coinSet) {
        this.coins = coinSet;
    }

    private HashMap<Integer, ArrayList<Integer>> buildLookupTable(int upTo) {
        HashMap<Integer, ArrayList<Integer>> lookup = new HashMap<>();
        lookup.put(0, new ArrayList<Integer>());

        for (int coin : coins) {
            var l = new ArrayList<Integer>();
            l.add(coin);
            lookup.put(coin, l);
        }

        for (int i = 1; i <= upTo; i++) {
            if (!lookup.containsKey(i)) {
                ArrayList<Integer> bestChoice = null;
                for (int coin : coins) {
                    int subProblem = i - coin;
                    if (subProblem >= 0) {
                        var maybeBetter = lookup.get(subProblem);
                        if (bestChoice == null && maybeBetter != null) {
                            bestChoice = (ArrayList<Integer>) maybeBetter.clone();
                            bestChoice.add(coin);
                        } else {
                            if (maybeBetter != null && maybeBetter.size() < bestChoice.size() - 1) {
                                bestChoice = (ArrayList<Integer>) maybeBetter.clone();
                                bestChoice.add(coin);
                            }
                        }
                    }
                }
                if (bestChoice != null) {
                    lookup.put(i, bestChoice);
                }
            }
        }

        return lookup;
    }

    public List<Integer> computeMostEfficientChange(int toChange) {

        if (toChange < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }

        var lookup = buildLookupTable(toChange);

        if (!lookup.containsKey(toChange)) {
            throw new IllegalArgumentException(
                    "The total " + toChange + " cannot be represented in the given currency.");
        }

        var res = lookup.get(toChange);
        res.sort((e1, e2) -> e1.compareTo(e2));
        return res;

    }
}
