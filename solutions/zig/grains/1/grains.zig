pub const ChessboardError = error{IndexOutOfBounds};

pub fn square(index: usize) ChessboardError!u64 {
    if (index < 1 or index > 64) {
        return ChessboardError.IndexOutOfBounds;
    }
    const _index: u6 = @as(u6, @intCast(index - 1));
    const one: u64 = 1;
    return one << _index;
}

pub fn total() u64 {
    return 0xFFFF_FFFF_FFFF_FFFF;
}
