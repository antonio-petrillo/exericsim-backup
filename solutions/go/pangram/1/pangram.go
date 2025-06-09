package pangram

import (
    "strings"
)

const checkPangram = 0x03FFFFFF 

func IsPangram(input string) bool {
    count := 0
	for _, letter := range strings.ToLower(input) {
        if letter >= 'a' && letter <= 'z' {
            count |= 1 << (letter - 'a');
        }
    }
    return count == checkPangram
}
