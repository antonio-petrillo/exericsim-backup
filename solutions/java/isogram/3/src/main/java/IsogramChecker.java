import java.util.Arrays;

class IsogramChecker {

    // boolean isIsogram(String phrase) {
    // var visited = new Boolean[26];
    // Arrays.fill(visited, Boolean.FALSE);
    // phrase = phrase.toLowerCase();
    // for (int i = 0; i < phrase.length(); i++) {
    // char c = phrase.charAt(i);
    // if (Character.isLetter(c)) {
    // int index = c - 97;
    // if (visited[index]) {
    // return false;
    // } else {
    // visited[index] = true;
    // }
    // }
    // }
    // return true;
    // }

    boolean isIsogram(String phrase) {
        int visited = 0;
        for (char c : phrase.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = c - 97;
                if ((visited & (1 << index)) != 0) {
                    return false;
                } else {
                    visited |= (1 << index);
                }
            }
        }
        return true;
    }

}
