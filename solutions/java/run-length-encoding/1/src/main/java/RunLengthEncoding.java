public class RunLengthEncoding {

    public String encode(String toEncode) {
        StringBuilder sb = new StringBuilder();
        while (!toEncode.equals("")) {
            int count = 0;
            char current = toEncode.charAt(0);
            for (char c : toEncode.toCharArray()) {
                if (c == current) {
                    count++;
                } else {
                    break;
                }
            }
            if (count > 1) {
                sb.append(String.format("%d%c", count, current));
            } else {
                sb.append(current);
            }
            toEncode = toEncode.substring(count);
        }
        return sb.toString();
    }

    public String decode(String toDecode) {
        StringBuilder sb = new StringBuilder();
        StringBuilder number = new StringBuilder();
        for (char c : toDecode.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                int numberParsed = 1;
                if (!number.toString().equals("")) {
                    numberParsed = Integer.parseInt(number.toString());
                }
                if (numberParsed != 1) {
                    sb.append((Character.toString(c)).repeat(numberParsed));
                } else {
                    sb.append(c);
                }
                number = new StringBuilder();
            }
        }
        return sb.toString();
    }
}
