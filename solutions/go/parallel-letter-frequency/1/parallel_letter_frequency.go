package letter

import "sync"

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

// ConcurrentFrequency counts the frequency of each rune in the given strings,
// by making use of concurrency.
func ConcurrentFrequency(texts []string) FreqMap {
	frequencies := FreqMap{}
    var mutex sync.Mutex
    var wg sync.WaitGroup
    wg.Add(len(texts))
    for _, s := range texts {
        go func(s string) {
            for _, c := range s {
                mutex.Lock()
           		frequencies[c]++
                mutex.Unlock()
            }
        	wg.Done()
        }(s)
    }
	wg.Wait()
	return frequencies
}
