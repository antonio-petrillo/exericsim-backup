class MicroBlog {
    public String truncate(String input) {
        return (input + "     ").substring(0, 4).trim();
        // if (input.length() < 5) {
        // return input;
        // }
        // return input.substring(0, 4);
    }
}
