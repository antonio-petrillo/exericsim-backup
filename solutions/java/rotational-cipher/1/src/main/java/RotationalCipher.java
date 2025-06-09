class RotationalCipher {

    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                int offset = Character.isLowerCase(c) ? 97 : 65;
                c = (char) ((((c - offset) + this.shiftKey) % 26) + offset);
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
