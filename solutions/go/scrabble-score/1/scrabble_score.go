package scrabble

import (
	"strings"
)

func rank(ch rune) int {
    switch ch {
    case 'a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't':
        return 1
    case 'd', 'g':
        return 2
    case 'b', 'c', 'm', 'p':
        return 3
    case 'f', 'h', 'v', 'w', 'y':
        return 4
    case 'k':
        return 5
    case 'j', 'x':
        return 8
    case 'q', 'z':
        return 10
    default:
        return 0
    }
}

func Score(word string) (score int) {
	for _, ch := range strings.ToLower(word) {
        score += rank(ch)
    }
    return 
}
