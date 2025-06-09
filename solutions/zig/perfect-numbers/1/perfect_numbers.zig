const std = @import("std");

pub const Classification = enum {
    deficient,
    perfect,
    abundant,
};

fn sumFactors(n: u64) u64 {
    var sum: u64 = 0;
    for (1..(n / 2 + 1)) |candidate| {
        if (n % candidate == 0) {
            sum += candidate;
        }
    }
    return sum;
}

pub fn classify(n: u64) Classification {
    std.debug.assert(n != 0);
    const sum = sumFactors(n);
    if (sum > n) {
        return Classification.abundant;
    } else if (sum < n) {
        return Classification.deficient;
    } else {
        return Classification.perfect;
    }
}
