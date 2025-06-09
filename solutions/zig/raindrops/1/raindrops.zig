const std = @import("std");

pub fn convert(buffer: []u8, n: u32) []const u8 {
    var fixed = std.io.fixedBufferStream(buffer);
    var out = fixed.writer();
    if (n % 3 == 0) {
        out.writeAll("Pling") catch unreachable;
    }
    if (n % 5 == 0) {
        out.writeAll("Plang") catch unreachable;
    }
    if (n % 7 == 0) {
        out.writeAll("Plong") catch unreachable;
    }
    if (fixed.pos == 0) {
        out.print("{}", .{n}) catch unreachable;
    }
    return fixed.getWritten();
}
