package blackjack

func ParseCard(card string) int {
	cards := map[string]int{
		"ace": 11, "two": 2, "three": 3, "four": 4,
		"five": 5, "six": 6, "seven": 7, "eight": 8,
		"nine": 9, "ten": 10, "jack": 10, "queen": 10,
		"king": 10, "other": 0,
	}
	return cards[card]
}

func largeHand(dealerSum int) string {
	if dealerSum < 10 {
		return "W"
	}
	return "S"
}

func smallHand(sum, dealerSum int) string {
	if sum >= 17 {
		return "S"
	} else if sum <= 11 {
		return "H"
	} else {
		if dealerSum >= 7 {
			return "H"
		} else {
			return "S"
		}
	}
}

// FirstTurn returns the decision for the first turn, given two cards of the
// player and one card of the dealer.
func FirstTurn(card1, card2, dealerCard string) string {
	// not very readable
	sum := ParseCard(card1) + ParseCard(card2)
	dealerSum := ParseCard(dealerCard)
	if sum == 22 {
		return "P"
	} else if sum == 21 {
		return largeHand(dealerSum)
	} else {
		return smallHand(sum, dealerSum)
	}
}
