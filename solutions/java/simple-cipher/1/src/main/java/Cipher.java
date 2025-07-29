import java.util.Iterator;
import java.util.Random;

public class Cipher {
    private final Key key;

    public Cipher() {
        StringBuilder sb = new StringBuilder();
        new Random().ints(100, 0, 26)
            .forEach(n -> sb.append(Character.valueOf((char)(n + 'a'))));
        this.key = new Key(sb.toString());
    }

    public Cipher(String key) {
        this.key = new Key(key);
    }

    public String getKey() {
        return key.key;
    }

    private String encode(String text, Op op) {
        Iterator<Integer> keyStream = key.iterator();
        StringBuilder sb = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            int key = keyStream.next();
            int offset = Math.floorMod(op == Op.ADD ? c - 'a' + key : c - 'a' - key, 26) + 'a';
            sb.append((char)offset);
        }
        return sb.toString();
    }

    public String encode(String plainText) {
        return encode(plainText, Op.ADD);
    }

    public String decode(String cipherText) {
        return encode(cipherText, Op.SUB);
    }

    // Utility Classes

    private enum Op {ADD, SUB};
    private static class Key implements Iterable<Integer> {
        final String key;

        Key(String key) {
            this.key = key;
        }

        public Iterator<Integer> iterator() {
            return new Iterator<Integer> () {
                int index = 0;

                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public Integer next() {
                    int old = index;
                    index = (index + 1) % key.length();
                    return key.charAt(old) - 'a';
                }
            };
        }
    }
}
