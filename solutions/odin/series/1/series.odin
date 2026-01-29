package series

Error :: enum {
	None,
	Invalid_Series_Length_Too_Large,
	Invalid_Series_Length_Zero,
	Invalid_Series_Length_Negative,
	Invalid_Sequence_Empty,
	Unimplemented,
}

series :: proc(sequence: string, series_len: int) -> ([]string, Error) {

    switch {
    case series_len < 0: return nil, .Invalid_Series_Length_Negative
    case series_len == 0: return nil, .Invalid_Series_Length_Zero
    case len(sequence) == 0: return nil, .Invalid_Sequence_Empty
    case series_len > len(sequence): return nil, .Invalid_Series_Length_Too_Large
    }

    nums := make([dynamic]string)

    for _, start in sequence {
        end := start + series_len
        if end > len(sequence) { break }

        append(&nums, sequence[start:end])
    }


    return nums[:], .None
}
