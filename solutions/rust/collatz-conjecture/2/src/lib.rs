pub fn collatz(n: u64) -> Option<u64> {
    if n <= 0 {
        return None;
    }
    let mut steps: u64 = 0;
    let mut curr: u64 = n;
    while curr != 1 {
        if curr % 2 == 0 {
            curr = curr >> 1;
        } else {
            curr = curr.checked_mul(3)?.checked_add(1)?;
        }
        steps += 1;
    }
    return Some(steps);
}
