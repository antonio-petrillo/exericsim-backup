import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.*;
import java.util.stream.IntStream;

public class DnDCharacter {
    private int strength;
    private int dexerity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public DnDCharacter(){
        strength = ability(rollDice());
        dexerity = ability(rollDice());
        constitution = ability(rollDice());
        intelligence = ability(rollDice());
        wisdom = ability(rollDice());
        charisma = ability(rollDice());
    }

    public int ability(List<Integer> scores) {
        return scores.stream()
            .mapToInt(Integer::intValue)
            .sorted()
            .skip(1)
            .sum();
    }

    public List<Integer> rollDice() {
        Random r = new Random();
        return Arrays.stream(IntStream.generate(() -> r.nextInt(1, 7))
                             .limit(4).toArray())
            .boxed()
            .toList();
    }

    public int modifier(int input) {
        return (int)Math.floor((input - 10) / 2.0);
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexerity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getHitpoints() {
        return 10 + modifier(getConstitution());
    }
}
