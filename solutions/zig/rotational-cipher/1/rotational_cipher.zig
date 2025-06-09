const std = @import("std");
const mem = std.mem;

pub fn rotate(allocator: mem.Allocator, text: []const u8, shiftKey: u5) mem.Allocator.Error![]u8 {
    var output = try allocator.alloc(u8, text.len);

    for (text, 0..) |c, i| {
        output[i] = switch (c) {
            'a'...'z' => @mod(c - 'a' + shiftKey, 26) + 'a',
            'A'...'Z' => @mod(c - 'A' + shiftKey, 26) + 'A',
            else => c,
        };
    }

    return output;
}
