package hamming

import "core:unicode/utf8"

Error :: enum {
	None,
	UnequalLengths,
	Unimplemented,
}

distance :: proc(strand1, strand2: string) -> (int, Error) {
    if len(strand1) != len(strand2) {
        return 0, .UnequalLengths
    }
    diff := 0
    runes := utf8.string_to_runes(strand2)
    defer delete(runes)
    for ch, i in strand1 {
        if ch != runes[i] {
            diff += 1
        }
    }
    return diff, .None
}
