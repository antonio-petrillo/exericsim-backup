const std = @import("std");
const mem = std.mem;

pub fn isBalanced(allocator: mem.Allocator, s: []const u8) !bool {
    var stack = std.ArrayList(u8).init(allocator);
    defer stack.deinit();
    for (s) |c| {
        switch (c) {
            '(', '[', '{' => {
                try stack.append(c);
            },
            ')' => {
                const top = stack.popOrNull();
                if (top == null or top.? != '(')
                    return false;
            },
            ']' => {
                const top = stack.popOrNull();
                if (top == null or top != '[')
                    return false;
            },
            '}' => {
                const top = stack.popOrNull();
                if (top == null or top != '{')
                    return false;
            },
            else => {},
        }
    }
    return stack.items.len == 0;
}
