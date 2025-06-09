import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.Spliterator;

// This is the worst exercise I've ever done in java
class RestApi {

    private final Map<String, User> users;
    private final Function<String, JSONObject> jsonizeUserFromName;
    
    RestApi(User... users) {
        this.users = new HashMap<>();
        for(var user : users) {
            this.users.put(user.name(), user);    
        }
                
        jsonizeUserFromName = name -> {
            double balance = 0.0;
            var user = this.users.get(name);
            var owes = new JSONObject();
            for (var to : user.owes()) {
                balance -= to.amount;
                owes.put(to.name, to.amount);
            }
            var owedBy = new JSONObject();
            for (var from : user.owedBy()) {
                balance += from.amount;
                owedBy.put(from.name, from.amount);
            }

            var json = new JSONObject();
            json.put("name", name);
            json.put("owes", owes);
            json.put("owedBy", owedBy);
            json.put("balance", balance);
            return json;
        };

    }

    String get(String url) {
        if (!url.equals("/users")) {
            return "{users: []}";
        }
        var json = new JSONObject();
        var users = this.users.keySet()
            .stream()
            .map(jsonizeUserFromName)
            .toList();
        json.put("users", users);    
        return json.toString();
    }

    String get(String url, JSONObject payload) {
        if (!url.equals("/users") || payload == null) {
            return "{users: []}";
        }
        var users = StreamSupport.stream(payload.getJSONArray("users").spliterator(), false)
            .map(name -> name.toString())
            .map(jsonizeUserFromName)
            .toList();
        var json = new JSONObject();
        json.put("users", users);    
        return json.toString();
    }

    String post(String url, JSONObject payload) {
        String user = "";
        if (url.equals("/add")) {
            user = payload.getString("user");
            var newUser = User.builder().setName(user).build();
            users.put(user, newUser);
        } else if (url.equals("/iou")) {
            var lender = payload.getString("lender");
            var borrower = payload.getString("borrower");
            double amount = payload.getDouble("amount");

            users.get(borrower).borrow(lender, amount);
            users.get(lender).lend(borrower, amount);

            var json = new JSONObject();
            var updated = new JSONArray();
            if (lender.compareTo(borrower) > 0) {
                updated.put(jsonizeUserFromName.apply(borrower));
                updated.put(jsonizeUserFromName.apply(lender));
            } else {
                updated.put(jsonizeUserFromName.apply(lender));
                updated.put(jsonizeUserFromName.apply(borrower));               
            }
            json.put("users", updated);
            return json.toString();
        }
        return jsonizeUserFromName.apply(user).toString();
    }

}