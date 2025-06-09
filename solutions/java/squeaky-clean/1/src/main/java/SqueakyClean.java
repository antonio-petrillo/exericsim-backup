class SqueakyClean {
    static String clean(String identifier) {
        identifier = identifier.replaceAll("[α-ω]", "");
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        for (char c : identifier.toCharArray()) {
            if (c == ' ') {
                result.append('_');
            } else if (c == '-') {
                nextUpper = !nextUpper;
            } else if (Character.isLetter(c)) {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = !nextUpper;
                } else {
                    result.append(c);
                }
            } else if (Character.isISOControl(c)) {
                result.append("CTRL");
            }
        }
        return result.toString();
    }
}
