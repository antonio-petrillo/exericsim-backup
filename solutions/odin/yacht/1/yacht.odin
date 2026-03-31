package yacht

import "core:slice"

Category :: enum {
	Ones,
	Twos,
	Threes,
	Fours,
	Fives,
	Sixes,
	Full_House,
	Four_Of_A_Kind,
	Little_Straight,
	Big_Straight,
	Yacht,
	Choice,
}

Roll :: [5]int

count_in_roll :: proc(dice: Roll, target: int) -> (count: int) {
	for roll in dice {
		if roll == target { count += 1 }
	}
	return
}

frequencies :: proc(dice: Roll) -> [6]int {
	freqs: [6]int
	for roll, i in dice {
		freqs[roll - 1] += 1
	}
	return freqs
}

@(rodata)
little_straight := Roll{1, 2, 3, 4, 5}
@(rodata)
big_straight := Roll{2, 3, 4, 5, 6}
@(rodata)
ones := Roll{1, 1, 1, 1, 1}

score :: proc(dice: Roll, category: Category) -> (score: int) {
	dice := dice
	slice.sort(dice[:])
	switch category {
	case .Ones, .Twos, .Threes, .Fours, .Fives, .Sixes:
		target := int(category) + 1
		score = count_in_roll(dice, target) * target
	case .Full_House:
		freqs := frequencies(dice)
		has_3, has_2 := false, false
		for freq in freqs {
			has_3 |= freq == 3
			has_2 |= freq == 2
		}
		if has_2 && has_3 {
			for roll in dice {
				score += roll
			}
		}
	case .Four_Of_A_Kind:
		freqs := frequencies(dice)
		for freq, i in freqs {
			if freq >= 4 {
				score = (i + 1) * 4
			}
		}
	case .Little_Straight:
		if dice == little_straight { score = 30 }
	case .Big_Straight:
		if dice == big_straight { score = 30 }
	case .Yacht:
		first := dice[0]
		if dice / first == ones { score = 50 }
	case .Choice:
		for roll in dice {
			score += roll
		}
	}
	return
}
