package dna

import "errors"

type DNA string

type Histogram map[rune]int

var InvalidDNA error = errors.New("Invalid DNA strands")

func (d DNA) Counts() (Histogram, error) {
	h := Histogram{'A': 0, 'C': 0, 'G': 0, 'T': 0}
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
