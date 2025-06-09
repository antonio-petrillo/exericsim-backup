package dna

import "errors"

type DNA string

type Histogram map[rune]int

var InvalidDNA error = errors.New("Invalid DNA strands")

func (d DNA) Counts() (Histogram, error) {
	h := make(map[rune]int)
	h['A'], h['C'], h['G'], h['T'] = 0, 0, 0, 0
	for _, ch := range d {
		switch ch {
		case 'A', 'C', 'G', 'T':
			h[ch]++
		default:
			return nil, InvalidDNA
		}
	}
	return h, nil
}
