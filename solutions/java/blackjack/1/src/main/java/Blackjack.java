import java.util.HashMap;

public class Blackjack {

    private HashMap<String, Integer> mapping;

    public Blackjack(){
        mapping = new HashMap();
        mapping.put("ace", 11);
        mapping.put("two", 2);
        mapping.put("three", 3);
        mapping.put("four", 4);
        mapping.put("five", 5);
        mapping.put("six", 6);
        mapping.put("seven", 7);
        mapping.put("eight", 8);
        mapping.put("nine", 9);
        mapping.put("ten", 10);
        mapping.put("jack", 10);
        mapping.put("queen", 10);
        mapping.put("king", 10);
    }

    public int parseCard(String card) {
        return mapping.get(card);
    }

    private int getSum(String card1, String card2) {
        return mapping.get(card1) + mapping.get(card2);
    }

    public boolean isBlackjack(String card1, String card2) {
        return getSum(card1, card2) == 21;
    }

    public String largeHand(boolean isBlackjack, int dealerScore) {
        if (isBlackjack && dealerScore < 10) {
           return "W";
        }
        return "S";
    }

    public String smallHand(int handScore, int dealerScore) {
        if (handScore >= 17) {
            return "S";
        } else if (handScore <= 11) {
            return "H";
        } else { // handScore in [12, 16]
            return dealerScore >= 7 ? "H" : "S";
        }
    }

    // FirstTurn returns the semi-optimal decision for the first turn, given the cards of the player and the dealer.
    // This function is already implemented and does not need to be edited. It pulls the other functions together in a
    // complete decision tree for the first turn.
    public String firstTurn(String card1, String card2, String dealerCard) {
        int handScore = parseCard(card1) + parseCard(card2);
        int dealerScore = parseCard(dealerCard);
        if (handScore == 22){
            return "P";
        } else if (20 < handScore) {
            return largeHand(isBlackjack(card1, card2), dealerScore);
        } else {
            return smallHand(handScore, dealerScore);
        }
    }
}
