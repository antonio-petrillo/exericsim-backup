import java.util.HashMap;

class ResistorColor {

    private HashMap<String, Integer> map;

    public ResistorColor(){
        map = new HashMap<>();
        map.put("black", 0);
        map.put("brown", 1);
        map.put("red", 2);
        map.put("orange", 3);
        map.put("yellow", 4);
        map.put("green", 5);
        map.put("blue", 6);
        map.put("violet", 7);
        map.put("grey", 8);
        map.put("white", 9);
    }

    int colorCode(String color) {
        // throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        return map.get(color);
    }

    String[] colors() {
        // throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        return new String[]{
            "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"
        };
    }
}
