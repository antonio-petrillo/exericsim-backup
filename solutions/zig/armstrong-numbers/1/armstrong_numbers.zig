const math = @import("std").math;

fn getPow(num: u128) u128 {
    var pow: u128 = 10;
    var step: u128 = 1;
    while (pow < num) {
        pow = pow *| 10;
        step += 1;
    }
    return step;
}

pub fn isArmstrongNumber(num: u128) bool {
    const pow = getPow(num);
    var armstrong: u128 = 0;
    var _num: u128 = num;
    while (_num != 0) : (_num = @divTrunc(_num, 10)) {
        armstrong = armstrong +| (math.powi(u128, @mod(_num, 10), pow) catch unreachable);
    }
    return armstrong == num;
}
