const std = @import("std");
const mem = std.mem;

pub fn toRna(allocator: mem.Allocator, dna: []const u8) mem.Allocator.Error![]const u8 {
    const buffer = try allocator.alloc(u8, dna.len);
    errdefer allocator.free(buffer);
    for (dna, 0..) |nucleotide, index| {
        buffer[index] = switch (nucleotide) {
            'A' => 'U',
            'C' => 'G',
            'G' => 'C',
            'T' => 'A',
            else => unreachable,
        };
    }
    return buffer;
}
