package letter

// FreqMap records the frequency of each rune in a given text.
type FreqMap map[rune]int

// Frequency counts the frequency of each rune in a given text and returns this
// data as a FreqMap.
func Frequency(text string) FreqMap {
	frequencies := FreqMap{}
	for _, r := range text {
		frequencies[r]++
	}
	return frequencies
}

func ConcurrentFrequency(texts []string) FreqMap {
    freqs := make(chan FreqMap, 8)
    for _, text := range texts {
        go func(s string) { // not required from go 1.20 on
            freqs <- Frequency(s)
        }(text)
    }
    frequencies := FreqMap{}
	for range texts {
        select {
            case freq := <- freqs:
        	for k, v := range freq {
                frequencies[k] += v
            } 
        }
    }
	return frequencies
}
