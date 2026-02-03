package grains

Error :: enum {
	None = 0,
	InvalidSquare,
	Unimplemented,
}

square :: proc(n: int) -> (u64, Error) {
    if n <= 0 || n > 64 { return 0, .InvalidSquare}
    return 1 << uint(n - 1), .None
}

total :: proc() -> (u64, Error) {
    return cast(u64)-1, .None
}
