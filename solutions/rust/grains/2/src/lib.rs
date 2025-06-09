pub fn square(s: u32) -> u64 {
    match s {
        1..=64 => 1 << (s - 1),
        _ => panic!("invalid square"),
    }
}

// can be done with 1 << 65 - 1
pub fn total() -> u64 {
    (1..=64).map(square).sum()
}
