const std = @import("std");
const mem = std.mem;

pub fn isBalanced(allocator: mem.Allocator, s: []const u8) !bool {
    var stack = std.ArrayList(u8).init(allocator);
    defer stack.deinit();
    outer: for (s) |c| {
        switch (c) {
            '{', '[', '(' => {
                try stack.append(c);
            },
            '}' => {
                if (stack.pop()) |top| {
                    if (top != '{') {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            ']' => {
                if (stack.pop()) |top| {
                    if (top != '[') {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            ')' => {
                if (stack.pop()) |top| {
                    if (top != '(') {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            else => continue :outer,
        }
    }
    return stack.items.len == 0;
}
