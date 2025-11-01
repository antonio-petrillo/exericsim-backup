import java.util.Arrays;
import java.util.List;

public class BookStore {

    private static final double BASE_PRICE = 8.0;

    public double calculateBasketCost(List<Integer> books) {
        if(books.isEmpty()) return .0;

        int[] frequencies = new int[5];
        for (var book : books) {
            frequencies[book - 1]++;
        }
        Arrays.sort(frequencies);

        int[] basket = new int[5];
        basket[0] = frequencies[0];

        for(int i = 1; i < 5; i++) {
            basket[i] = frequencies[i] - frequencies[i - 1];
        }

        int min = Math.min(basket[0], basket[2]);
        if (min > 0) {
            basket[1] += min * 2;
            basket[0] -= min;
            basket[2] -= min;
        }
    
        return BASE_PRICE * (basket[0] * 5 * 0.75
                             + basket[1] * 4 * 0.8
                             + basket[2] * 3 * 0.9
                             + basket[3] * 2 * 0.95
                             + basket[4]);
    }

}
