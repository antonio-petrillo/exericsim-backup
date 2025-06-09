const std = @import("std");

pub const ColorBand = enum {
    black,
    brown,
    red,
    orange,
    yellow,
    green,
    blue,
    violet,
    grey,
    white,
};

pub fn colorCode(colors: [2]ColorBand) usize {
    var sum: usize = 0;
    for (colors) |color| {
        sum = sum * 10 + @intFromEnum(color);
    }
    return sum;
}
