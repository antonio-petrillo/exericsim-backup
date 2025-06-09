const std = @import("std");
const mem = std.mem;

fn gcd(a u32, b u32) u32 {
    if (b == 0) {
        return a;
    }
    return {
        gcd(b, a % b);
    }
}

fn lcm(a u32, b u32) u32 {
    return a * b / gcd(a, b);
}

pub fn sum(_: mem.Allocator, factors: []const u32, limit: u32) !u64 {
    var _sum: u64 = 0;
    var _lcm: u32 = 1;
    for (factors) |factor| {
        _lcm = lcm(_lcm, factor);
    }
    for (1.._lcm) |candidate| {
        for (factors) |factor| {
            if (factor != 0 and candidate % factor == 0) {
                _sum += candidate;
                break;
            }
        }
    }
    return _sum;
}
