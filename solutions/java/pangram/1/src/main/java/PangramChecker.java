import java.util.HashSet;

public class PangramChecker {

    public boolean isPangram(String input) {
        // throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        var hits = new HashSet<Character>();
        for(char c : input.toLowerCase().toCharArray()){
            if(Character.isLetter(c)){
                hits.add(c);
            } 
        }
        return hits.size() == 26;
    }

}
