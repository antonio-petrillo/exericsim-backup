import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

/** POJO representing a User in the database. */
public class User {
    private final String name;
    private final List<Iou> owes;
    private final List<Iou> owedBy;

    private User(String name, List<Iou> owes, List<Iou> owedBy) {
        this.name = name;
        this.owes = new ArrayList<>(owes);
        this.owedBy = new ArrayList<>(owedBy);
    }

    public String name() {
        return name;
    }

    public void lend(String name, double amount) {
        update(name, amount, owes, owedBy);
    }

    public void borrow(String name, double amount) {
        update(name, amount, owedBy, owes);
    }
    
    private void update(String name, double amount, List<Iou> l1, List<Iou> l2) {
        var iter = l1.iterator();
        boolean found = false;
        while(iter.hasNext()) {
            var iou = iter.next();
            if (iou.name.equals(name)) {
                iter.remove();
                found = true;
                amount = iou.amount - amount;
                break;
            }
        }
        if (found && amount > 0) {
            l1.add(new Iou(name, amount));
            return;
        }
        if (amount == 0) {
            return;
        }
        amount = amount > 0 ? amount  : amount * -1.0;
        iter = l2.iterator();
        while(iter.hasNext()) {
            var iou = iter.next();
            if(iou.name.equals(name)) {
                iter.remove();
                amount += iou.amount;
                break;
            }
        }
        l2.add(new Iou(name, amount));
    }

    /** IOUs this user owes to other users. */
    public List<Iou> owes() {
        return unmodifiableList(owes);
    }

    /** IOUs other users owe to this user. */
    public List<Iou> owedBy() {
        return unmodifiableList(owedBy);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private final List<Iou> owes = new ArrayList<>();
        private final List<Iou> owedBy = new ArrayList<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder owes(String name, double amount) {
            owes.add(new Iou(name, amount));
            return this;
        }

        public Builder owedBy(String name, double amount) {
            owedBy.add(new Iou(name, amount));
            return this;
        }

        public User build() {
            return new User(name, owes, owedBy);
        }
    }
}
