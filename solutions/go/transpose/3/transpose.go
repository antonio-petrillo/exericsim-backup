package transpose

import "bytes"

const space = rune(' ')

func computeTransposedLengths(input []string) (int,[]int) {
    lengths := []int{}
    max := -1
    for i := len(input) - 1; i >= 0; i-- {
        if len(input[i]) > max {
            max = len(input[i])
        }
        lengths = append(lengths, max)
    }
    return max, lengths
}

func Transpose(input []string) (result []string) {
    max, lengths := computeTransposedLengths(input)
    if max <= 0 {
        return 
    }
    buffers := make([]bytes.Buffer, max)
    size := len(lengths)
	for i, s := range input {
        for j, ch := range s {
            buffers[j].WriteRune(ch)
        }
        for j := len(s); j < lengths[size - i - 1]; j++ {
			buffers[j].WriteRune(space)          
        }
    }
    for _, buf := range buffers {
        result = append(result, buf.String())
    }
    return 
}
