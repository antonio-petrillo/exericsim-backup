package isogram

import "strings"

func IsIsogram(word string) bool {
	letters := 0
	for _, ch := range strings.ToLower(word) {
		if ch < 'a' || ch > 'z' {
			continue
		}
		id := 1 << int(ch-'a')
		if letters&id != 0 {
			return false
		}
		letters |= id
	}
	return true
}
