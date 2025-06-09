import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class HandshakeCalculator {

    public List<Signal> calculateHandshake(int number) {
        List<Signal> handshake = new ArrayList<Signal>();
        Signal[] signals = { Signal.WINK, Signal.DOUBLE_BLINK, Signal.CLOSE_YOUR_EYES, Signal.JUMP };
        for (int i = 0; i < 4; i++) {
            int shift = (1 << i);
            if ((number & shift) != 0) {
                handshake.add(signals[i]);
            }
        }
        if ((number & 1 << 4) != 0) {
            Collections.reverse(handshake);
        }
        return handshake;
    }

}
