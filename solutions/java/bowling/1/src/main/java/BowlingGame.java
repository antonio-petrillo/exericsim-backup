import java.util.*;

public class BowlingGame {

    private boolean gameEnded = false;
    private Frame[] frames;
    private int index = 0;
    private int bonusRoll = 0;

    public BowlingGame() {
        frames = new Frame[11];
        frames[10] = new Frame(null);
        for (int i = 9; i >= 0; i--) {
            frames[i] = new Frame(frames[i + 1]);
        }
    }

    public void roll(int pin) {
        if (gameEnded) {
            throw new IllegalStateException("Cannot roll after game is over");
        }
        frames[index].roll(pin);
    }

    public int score() {
        if (!gameEnded) {
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        }
        int score = 0;
        for (int i = 9; i >= 0; i--) {
            score += frames[i].score();
        }
        return score;
    }

    private enum FrameType {
        OPEN, SPARE, STRIKE;
    }

    private class Frame {
        int first = -1;
        int second = 0;
        FrameType type = FrameType.OPEN;
        Frame next;

        Frame(Frame next) {
            this.next = next;
        }

        boolean notValidNormalRoll(int pin) {
            return index <= 9 && (pin > 10 || first + pin > 10);
        }

        boolean notValidLast10TH(int pin) {
            return index <= 10 && (pin > 10 || (first < 10 && first + pin > 10));
        }

        void validRoll(int pin) {
            if (pin < 0) {
                throw new IllegalStateException("Negative roll is invalid");
            }
            if (notValidNormalRoll(pin) || notValidLast10TH(pin)) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
        }

        void roll(int pin) {
            validRoll(pin);
            if (index <= 9)
                normalRoll(pin);
            else
                lastRoll(pin);
        }

        void normalRoll(int pin) {
            if (first == -1) {
                first = pin;
                if (pin == 10) {
                    type = FrameType.STRIKE;
                    index++;
                }
                if (type == FrameType.STRIKE && index == 9) {
                    bonusRoll += 2;
                }
            } else {
                second = pin;
                if (first + second == 10) {
                    type = FrameType.SPARE;
                }
                if (index == 9 && type == FrameType.OPEN) {
                    gameEnded = true;
                }
                if (type == FrameType.SPARE && index == 9) {
                    bonusRoll++;
                }
                index++;
            }
        }

        void lastRoll(int pin) {
            if (first == -1) {
                first = pin;
                if (bonusRoll == 1) {
                    gameEnded = true;
                }
            } else {
                second = pin;
                gameEnded = true;
            }
        }

        int score() {
            if (next == null || type == FrameType.OPEN) {
                return first + second;
            }
            if (type == FrameType.STRIKE) {
                second = next.first;
                return 10 + next.first + next.second;
            } else { // type == SPARE
                return 10 + next.first;
            }
        }

    }

}
