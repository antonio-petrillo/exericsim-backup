package pangram

import (
    "unicode"
)

const checkPangram = 0x03FFFFFF 

func IsPangram(input string) bool {
    count := 0
	for _, letter := range input {
        letter = unicode.ToLower(letter)
        if letter >= 'a' && letter <= 'z' {
            count |= 1 << (letter - 'a');
        }
    }
    return count == checkPangram
}
