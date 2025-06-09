import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class RelationshipComputer<T> {
    public Relationship computeRelationship(List<T> firstList, List<T> secondList) {
        if (firstList.equals(secondList)){
            return Relationship.EQUAL;
        }
        if (firstList.size() > secondList.size()){
            return helper(firstList, secondList);
        } else {
            var rel = helper(secondList, firstList);
            if (rel == Relationship.SUPERLIST) {
                return Relationship.SUBLIST;
            } else {
                return rel;
            }
        }
    }

    private Relationship helper(List<T> firstList, List<T> secondList){
        ArrayList<T> firstArr = new ArrayList<T>(firstList);
        int i = -1;
        outer:
        while (i < firstArr.size()) {
            i++;
            var iter = secondList.iterator();
            int offset = 0;
            while(iter.hasNext()){
               T elem = iter.next();
               if (i + offset >= firstArr.size() || !elem.equals(firstArr.get(i + offset))) {
                   continue outer;
               } else {
                   offset++;
               }
            }
            if (offset == secondList.size()) {
                return Relationship.SUPERLIST;
            }
        }
        return  Relationship.UNEQUAL;
    }
}
