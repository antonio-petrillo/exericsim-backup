package collatz_conjecture

// Returns the number of steps to get to a value of 1.
steps :: proc(start: int) -> (result: int, ok: bool) {
    if start <= 0 { return }

    ok = true
    start := start

    for start != 1 {
        result += 1

        if start & 1 == 0 {
            start >>= 1
        } else {
            start = start * 3 + 1
        }

    }

    return
}
