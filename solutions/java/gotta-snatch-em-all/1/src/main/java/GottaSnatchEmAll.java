import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards); 
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        var uncommonCards = new HashSet<String>(theirCollection);
        uncommonCards.removeAll(myCollection);
        return !uncommonCards.isEmpty() && !myCollection.isEmpty() && !theirCollection.isEmpty();
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        if (collections.isEmpty()) {
            return new HashSet<String>();
        } else if (collections.size() == 1) {
            return collections.get(0);
        }
        var result = new HashSet<String>();
        Set<String> first = collections.get(0);
        List<Set<String>> rest = collections.subList(1, collections.size());
        outer: // good example of a useful goto
        for (var card : first) {
            for (var collection : rest) {
              if(!collection.contains(card)) {
                  continue outer;
              }  
            }
            result.add(card);
        }
        return result;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> result = new HashSet<>();
        for(var collection : collections) {
            result.addAll(collection);
        }
        return result;
    }
}
