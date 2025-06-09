package transpose

func computeTransposedLength(input []string) (int,[]int) {
    lengths := []int{}

    max := -1
    for i := len(input) - 1; i >= 0; i-- {
        if len(input[i]) > max {
            max = len(input[i])
        }
        lengths = append(lengths, max)
    }
    for i,j := 0, len(lengths) - 1; i < j; i,j = i + 1, j - 1 {
        lengths[i], lengths[j] = lengths[j], lengths[i]
    }
    return max, lengths
}

func Transpose(input []string) []string {
    max, lengths := computeTransposedLength(input)
    if max <= 0 {
        return []string{}
    }
    result := make([]string, max)
	for i, s := range input {
        for j, ch := range s {
            result[j] = result[j] + string(ch)
        }
        for j := len(s); j < lengths[i]; j++ {
			result[j] = result[j] + " "            
        }
    }
    return result
}
