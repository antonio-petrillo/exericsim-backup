pub const NucleotideError = error{Invalid};

pub const Counts = struct {
    a: u32,
    c: u32,
    g: u32,
    t: u32,
};

fn validNucleotide(c: u8) bool {
    return c == 'A' or c == 'C' or c == 'G' or c == 'T';
}

pub fn countNucleotides(s: []const u8) NucleotideError!Counts {
    var counts = Counts{ .a = 0, .c = 0, .g = 0, .t = 0 };
    for (s) |c| {
        switch (c) {
            'A' => {
                counts.a = counts.a + 1;
            },
            'C' => {
                counts.c = counts.c + 1;
            },
            'G' => {
                counts.g = counts.g + 1;
            },
            'T' => {
                counts.t = counts.t + 1;
            },
            else => {
                return NucleotideError.Invalid;
            },
        }
    }
    return counts;
}
