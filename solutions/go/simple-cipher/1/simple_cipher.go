package cipher

import (
	"math/rand"
	"strings"
)

type vigenere struct {
	dists []int
}

type shift struct {
	vigenere
}

func shiftBy(ch rune, by int) rune {
	if by < 0 {
		by += 26
	}
	return (ch-'a'+rune(by))%26 + 'a'
}

func NewCaesar() Cipher {
	return vigenere{
		dists: []int{rand.Intn(25) + 1},
	}
}

func NewShift(distance int) Cipher {
	if n := distance * distance; n < 1 || n > 625 {
		return nil
	}
	return vigenere{
		dists: []int{distance},
	}
}

func NewVigenere(key string) Cipher {
	count := 0
	distances := []int{}
	for _, ch := range key {
		if ch < 'a' || ch > 'z' {
			return nil
		}
		if ch == 'a' {
			count++
		}
		distances = append(distances, int(ch-'a'))
	}
	if count == len(key) {
		return nil
	}
	return vigenere{
		dists: distances,
	}
}

func (v vigenere) Encode(input string) string {
	sb := strings.Builder{}
	offset := 0
	for _, ch := range strings.ToLower(input) {
		if ch >= 'a' && ch <= 'z' {
			sb.WriteRune(shiftBy(ch, v.dists[offset%len(v.dists)]))
			offset++
		}
	}
	return sb.String()
}

func (v vigenere) Decode(input string) string {
	sb := strings.Builder{}
	offset := 0
	for _, ch := range strings.ToLower(input) {
		if ch >= 'a' && ch <= 'z' {
			sb.WriteRune(shiftBy(ch, -v.dists[offset%len(v.dists)]))
			offset++
		}
	}
	return sb.String()
}
