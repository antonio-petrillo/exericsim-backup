import java.util.List;
import java.util.ArrayList;

public class Allergies {
    private List<Allergen> allergens = new ArrayList<>();
    private int score;
    
    public Allergies(int score) {
        this.score = score;

        for (var allergen : Allergen.values()) {
            if ((score & allergen.getScore()) != 0)
                allergens.add(allergen);
        }
    }

    public boolean isAllergicTo(Allergen allergen) {
        return (score & allergen.getScore()) != 0;
    }

    public List<Allergen> getList() {
        return allergens;
    }
}
