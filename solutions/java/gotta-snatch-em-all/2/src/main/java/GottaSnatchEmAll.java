import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards); 
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        var myUncommon = new HashSet<String>(myCollection);
        var theirUncommon = new HashSet<String>(theirCollection);
        myUncommon.removeAll(theirCollection);
        theirUncommon.removeAll(myCollection);
        return !myUncommon.isEmpty() && !theirUncommon.isEmpty();
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
	    return collections.stream()
            .flatMap(Set::stream)
			.collect(Collectors.toSet());
    }
}
