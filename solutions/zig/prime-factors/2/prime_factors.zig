const std = @import("std");
const mem = std.mem;

pub fn factors(allocator: mem.Allocator, value: u64) mem.Allocator.Error![]u64 {
    var list = std.ArrayList(u64).init(allocator);
    errdefer list.deinit();

    var n = value;
    while (n % 2 == 0) : (n /= 2) {
        try list.append(2);
    }
    var factor: u64 = 3;
    while (n > 1) {
        if (n % factor == 0) {
            try list.append(factor);
            n /= factor;
        } else if (factor * factor > n) {
            try list.append(n);
            break;
        } else {
            factor += 2;
        }
    }

    return list.toOwnedSlice();
}
