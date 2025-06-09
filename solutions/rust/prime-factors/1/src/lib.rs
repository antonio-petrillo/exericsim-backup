pub fn factors(n: u64) -> Vec<u64> {
    let mut factors: Vec<u64> = Vec::new();
    let mut curr_factor = 2;
    let mut curr_n = n;
    while curr_n % 2 == 0 {
        factors.push(curr_factor);
        curr_n = curr_n >> 1;
    }
    curr_factor += 1;
    while curr_n != 1 {
        if curr_n % curr_factor == 0 {
            factors.push(curr_factor);
            curr_n /= curr_factor;
        } else {
            curr_factor += 2;
        }
    }
    return factors;
}
