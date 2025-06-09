pub const ComputationError = error{IllegalArgument};

pub fn steps(number: usize) anyerror!usize {
    if (number <= 0) {
        return ComputationError.IllegalArgument;
    }
    var res: usize = 0;
    var mutIn = number;
    while (mutIn > 1) : (res = res + 1) {
        mutIn = if (mutIn % 2 == 0) mutIn / 2 else mutIn * 3 + 1;
    }
    return res;
}
