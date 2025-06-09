const std = @import("std");
const mem = std.mem;

pub fn sum(allocator: mem.Allocator, factors: []const u32, limit: u32) !u64 {
    var _sum: u64 = 0;
    var set = std.hash_map.AutoHashMap(u32, void).init(allocator);
    defer set.deinit();
    for (factors) |factor| if (factor > 0) {
        var n: u32 = factor;
        while (n < limit) : (n += factor) {
            try set.put(n, {});
        }
    };
    var iterator = set.keyIterator();
    while (iterator.next()) |elem| {
        _sum += elem.*;
    }
    return _sum;
}
