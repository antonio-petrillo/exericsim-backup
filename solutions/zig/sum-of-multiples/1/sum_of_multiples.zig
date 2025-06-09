const std = @import("std");
const mem = std.mem;

pub fn sum(_: mem.Allocator, factors: []const u32, limit: u32) !u64 {
    var _sum: u64 = 0;
    for (1..limit) |candidate| {
        for (factors) |factor| {
            if (factor != 0 and candidate % factor == 0) {
                _sum += candidate;
                break;
            }
        }
    }
    return _sum;
}
