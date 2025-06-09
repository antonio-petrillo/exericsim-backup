const std = @import("std");

pub fn primes(buffer: []u32, comptime limit: u32) []u32 {
    var set = std.StaticBitSet(limit + 1).initEmpty();
    set.set(0);
    set.set(1);

    var n: u32 = 2;
    var offset: usize = 0;

    while (n <= limit) : (n = n + 1) {
        if (set.isSet(n)) continue;
        buffer[offset] = n;
        offset += 1;
        var multiples: u32 = n + n;
        while (multiples <= limit) : (multiples += n) {
            set.set(multiples);
        }
    }

    return buffer[0..offset];
}
