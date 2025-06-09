const expected: u26 = 0x3FF_FFFF;

fn getIndex(character: u8) u26 {
    var result: u26 = 0;
    if (character >= 65 and character <= 90) {
        result |= @as(u26, 1) << @as(u5, @intCast(character - 65));
    }
    if (character >= 97 and character <= 122) {
        result |= @as(u26, 1) << @as(u5, @intCast(character - 97));
    }
    return result;
}

pub fn isPangram(str: []const u8) bool {
    var result: u26 = 0;
    for (str) |c| {
        const index: u26 = getIndex(c);
        if (index != 0) {
            result |= index;
        }
    }
    return result == expected;
}
