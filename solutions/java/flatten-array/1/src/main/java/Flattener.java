import java.util.List;
import java.util.ArrayList;

public class Flattener {

    public List<Object> flatten(List<?> list) {
        return list.stream()
            .map(elem -> {
                    if (elem == null) return null;
                    if (elem instanceof List l) {
                        return flatten(l);
                    }
                    return List.<Object>of(elem);
                })
            .filter(elem -> elem != null)
            .reduce(new ArrayList<Object>(), (acc, el) -> {
                    acc.addAll(el);  
                    return acc;
                });
    }

}
