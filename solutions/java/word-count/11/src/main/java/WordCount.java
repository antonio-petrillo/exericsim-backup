import java.util.Map;
import java.util.HashMap;

class WordCount {

    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> wordCount = new HashMap<>();
        input = input.toLowerCase();
        String[] words = input.split(" |,");
        for(int i = 0; i < words.length; i++){
            words[i] = words[i].replaceAll("[^a-zA-Z0-9']", " ");
            words[i] = words[i].replaceAll(" +", "");
            words[i] = words[i].replaceAll("(^')|('$)", "");
            words[i] = words[i].replaceAll("\\.$", "");
        }
        for(var word : words){
            if(word.length() > 0){
                if(!wordCount.containsKey(word)){
                    wordCount.put(word, 1);
                } else {
                    var occurrence = wordCount.get(word);
                    wordCount.put(word, occurrence + 1);
                }
            }
        }

        return wordCount;
    }


}
