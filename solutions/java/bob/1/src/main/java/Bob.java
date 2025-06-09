public class Bob {

    public String hey(String input) {
        boolean yank = input.trim().equals("");
        boolean question = input.trim().endsWith("?");
        boolean yell = input.equals(input.replaceAll("[a-z]", "")) && input.matches(".*[A-Z].*");
        if (yell && question) {
            return "Calm down, I know what I'm doing!";
        } else if (yank) {
            return "Fine. Be that way!";
        } else if (question) {
            return "Sure.";
        } else if (yell) {
            return "Whoa, chill out!";
        } else {
            return "Whatever.";
        }
    }

}
