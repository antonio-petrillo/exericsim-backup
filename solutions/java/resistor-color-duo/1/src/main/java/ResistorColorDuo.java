import java.util.HashMap;

class ResistorColorDuo {

    private static HashMap<String, Integer> colorMapping = new HashMap<>();

    static {
        colorMapping.put("black", 0);
        colorMapping.put("brown", 1);
        colorMapping.put("red", 2);
        colorMapping.put("orange", 3);
        colorMapping.put("yellow", 4);
        colorMapping.put("green", 5);
        colorMapping.put("blue", 6);
        colorMapping.put("violet", 7);
        colorMapping.put("grey", 8);
        colorMapping.put("white", 9);
    }

    int value(String[] colors) {
        int result = 0;
        int index = 0;
        while (index < 2 && index < colors.length) {
            result = result * 10 + colorMapping.get(colors[index]);
            index++;
        }
        return result;
    }

}
