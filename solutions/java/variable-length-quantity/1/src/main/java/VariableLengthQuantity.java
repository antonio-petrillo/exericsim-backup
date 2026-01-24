import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class VariableLengthQuantity {

    // wtf, assume input at most 32 bit and then use Long?
    // why 'List<String>'? I feel like is just a comfy way to test
    public List<String> encode(List<Long> numbers) {
        return numbers.stream()
            .map(num -> {
                    var encoded = new LinkedList<Long>();
                    encoded.addFirst(num & 0x7F);
                    num >>= 0x7;
                    while (num > 0) {
                        encoded.addFirst((num & 0x7F) | 0x80);
                        num >>= 0x7;
                    }
                    return encoded;
                })
            .flatMap(bytes -> bytes.stream())
            .map(num -> String.format("0x%x", num))
            .toList();
    }

    // wtf is this API?
    // Long to represent Byte?
    // And the List<String>? no idea
    public List<String> decode(List<Long> bytes) {
        var decoded = new ArrayList<String>();

        var iter = bytes.iterator();
        while (iter.hasNext()) {
            long num = 0;

            while (true) {
                if (!iter.hasNext()) {
                    throw new IllegalArgumentException("Invalid variable-length quantity encoding");
                }
                long n = iter.next();
                if (n > 0xff)
                    throw new IllegalArgumentException("Input contains value that is not a byte");

                byte b = (byte) n;
                num = (num << 7) | (b & 0x7F);
                if ((b & 0x80) == 0)
                    break;
            }

            decoded.add(String.format("0x%x", num));
        }

        return decoded;
    }
}
