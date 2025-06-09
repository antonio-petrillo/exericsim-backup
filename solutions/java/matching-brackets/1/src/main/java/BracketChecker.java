import java.util.LinkedList;
import java.util.List;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/
public class BracketChecker {
    private String toCheck;

    public BracketChecker(String toCheck) {
        this.toCheck = toCheck;
    }

    public boolean areBracketsMatchedAndNestedCorrectly() {
        LinkedList<Character> stack = new LinkedList<>();

        for (char c : toCheck.toCharArray()) {
            switch (c) {
            case '(':
            case '[':
            case '{':
                stack.push(c);
                break;
            case ')':
                if (stack.size() == 0 || stack.pop() != '(')
                    return false;
                break;
            case ']':
                if (stack.size() == 0 || stack.pop() != '[')
                    return false;
                break;
            case '}':
                if (stack.size() == 0 || stack.pop() != '{')
                    return false;
                break;

            }
        }

        return stack.isEmpty();
    }

}
