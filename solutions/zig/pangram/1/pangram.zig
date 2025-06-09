const expected: u32 = 0x03FF_FFFF;

fn getIndex(character: u32) u32 {
    var result: u32 = 0;
    if (character >= 65 and character <= 90) {
        const index: u32 = @as(u32, 1) << @as(u5, @intCast(character - 65));
        result |= index;
    }
    if (character >= 97 and character <= 122) {
        const index: u32 = @as(u32, 1) << @as(u5, @intCast(character - 97));
        result |= index;
    }
    return result;
}

pub fn isPangram(str: []const u8) bool {
    var result: u32 = 0;
    for (str) |c| {
        var index: u32 = getIndex(c);
        if (index != 0) {
            result |= index;
        }
    }
    return result == expected;
}
