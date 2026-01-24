public class RotationalCipher {

    private int shiftKey;

    public RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    public String rotate(String data) {
        return data.codePoints()
            .mapToObj(ch -> switch (Character.getType(ch)) {
                case Character.UPPERCASE_LETTER -> shift(ch, 'A');
                case Character.LOWERCASE_LETTER -> shift(ch, 'a');
                default -> ch;
                })
            .collect(StringBuilder::new,
                     StringBuilder::appendCodePoint,
                     StringBuilder::append)
            .toString();
    }


    private int shift(int letter, char start) {
        return start + (letter - start + shiftKey) % 26;
    }

}
