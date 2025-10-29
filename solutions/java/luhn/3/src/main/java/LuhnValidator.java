public class LuhnValidator {

    public boolean isValid(String candidate) {
        int sum = 0;
        int zeroCount = 0;
        boolean oddPosition = false;

        for (int i = candidate.length() - 1; i >= 0; i--) {
            char c = candidate.charAt(i);
            if (c >= '0' && c <= '9') {
                zeroCount += c == '0' ? 1 : 0;
                int num = c - '0';
                if (oddPosition) {
                    num <<= 1;
                    num = num > 9 ? num - 9 : num;
                }
                oddPosition = !oddPosition;
                sum += num;
            } else if (c != ' ') return false;
        }
        
        return sum == 0 && zeroCount > 1 || sum > 0 && sum % 10 == 0;
    }

}
