import java.util.List;
import java.util.stream.IntStream;

public class CryptoSquare {

    private final String encrypted;

    public CryptoSquare(String plaintext) {
        encrypted = encrypt(plaintext);
    }

    public static String encrypt(String plaintext) {
        var normalized = plaintext.replaceAll("[^A-Za-z0-9]", "")
            .toLowerCase();

        if (normalized.isEmpty()) return "";
        if (normalized.length() == 1) return normalized;

        var sqrt = Math.sqrt(normalized.length());
        final int col = (int) Math.ceil(sqrt);
        final int row = (int) Math.round(sqrt);

        int padding = row * col - normalized.length();
        var sb = new StringBuilder(normalized);

        while (padding > 0) {
            switch (padding) {
            case 1:
                sb.append(" ");
                padding--;
            case 2:
                sb.append("  ");
                padding -= 2;
            case 3:
                sb.append("   ");
                padding -= 3;
            default: // case 4:
                sb.append("    ");
                padding -= 4;
            }
        }

        final var text = sb.toString();

        var rows = IntStream.iterate(0, r -> r + col)
            .limit(row)
            .mapToObj(start -> text.substring(start, start + col))
            .toList();

        sb = new StringBuilder();

        for (int j = 0; j < col; j++) {
            for (var str : rows) {
                sb.append(str.charAt(j));
            }
            sb.append(' ');
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getCiphertext() {
        return encrypted;
    }

}
