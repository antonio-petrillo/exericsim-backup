pub const SeriesError = error{
    InvalidCharacter,
    NegativeSpan,
    InsufficientDigits,
};

pub fn largestProduct(digits: []const u8, span: i32) SeriesError!u64 {
    if (span < 0) return SeriesError.NegativeSpan;
    if (span > digits.len) return SeriesError.InsufficientDigits;
    if (span == 0) return 1;

    const size: usize = @intCast(span);
    var max: u64 = 0;
    for (0..digits.len - size + 1) |i| {
        var product: u64 = 1;
        for (digits[i .. i + size]) |digit| {
            if (digit < '0' or digit > '9') return SeriesError.InvalidCharacter;
            product *= digit - '0';
        }
        max = @max(max, product);
    }
    return max;
}
