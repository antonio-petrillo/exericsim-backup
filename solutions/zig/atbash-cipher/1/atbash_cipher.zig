const std = @import("std");
const mem = std.mem;

/// Encodes `s` using the Atbash cipher. Caller owns the returned memory.
pub fn encode(allocator: mem.Allocator, s: []const u8) mem.Allocator.Error![]u8 {
    var list = std.ArrayList(u8).init(allocator);
    var i: usize = 0;
    outer: for (s) |c| {
        const e: u8 = switch (c) {
            '0'...'9' => c,
            'a'...'z' => 'z' - (c - 'a'),
            'A'...'Z' => 'z' - (c - 'A'),
            else => continue :outer,
        };
        try list.append(e);
        i = i + 1;
        if (@mod(i, 5) == 0 and i != s.len - 1) {
            try list.append(' ');
        }
    }
    while (list.items.len > 0 and list.getLast() == ' ') {
        _ = list.pop();
    }

    return list.toOwnedSlice();
}

/// Decodes `s` using the Atbash cipher. Caller owns the returned memory.
pub fn decode(allocator: mem.Allocator, s: []const u8) mem.Allocator.Error![]u8 {
    var list = std.ArrayList(u8).init(allocator);
    outer: for (s) |c| {
        try list.append(switch (c) {
            '0'...'9' => c,
            'a'...'z' => 'a' + ('z' - c),
            'A'...'Z' => 'a' + ('Z' - c),
            else => continue :outer,
        });
    }
    return list.toOwnedSlice();
}
