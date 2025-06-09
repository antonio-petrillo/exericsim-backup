pub const ChessboardError = error{IndexOutOfBounds};

pub fn square(index: usize) ChessboardError!u64 {
    if (index < 1 or index > 64) {
        return ChessboardError.IndexOutOfBounds;
    }
    return @as(u64, 1) << @as(u6, @intCast(index - 1));
}

pub fn total() u64 {
    return 0xFFFF_FFFF_FFFF_FFFF;
}
