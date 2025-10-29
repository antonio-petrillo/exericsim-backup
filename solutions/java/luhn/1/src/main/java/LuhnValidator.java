public class LuhnValidator {

    public boolean isValid(String candidate) {
        int sum = 0;
        int zeroCount = 0;
        boolean oddPosition = false;

        for (int i = candidate.length() - 1; i >= 0; i--) {
            char c = candidate.charAt(i);
            if (c >= '0' && c <= '9') {
                zeroCount += c == '0' ? 1 : 0;
                if (oddPosition) {
                    int doubled = (c - '0') * 2;
                    if (doubled > 9) doubled -= 9;
                    sum += doubled;
                    oddPosition = false;
                } else {
                    sum += c - '0';
                    oddPosition = true;
                }
            } else if (c != ' ') return false;
        }
        
        return sum == 0 && zeroCount > 1 || sum >0 && sum % 10 == 0;
    }

}
