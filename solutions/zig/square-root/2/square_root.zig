const std = @import("std");

pub fn squareRoot(radicand: usize) usize {
    if (radicand <= 1) return radicand;

    var x0: usize = radicand >> 1;
    var x1: usize = (x0 + radicand / x0) >> 1;

    while (x1 < x0) {
        x0 = x1;
        x1 = (x0 + radicand / x0) >> 1;
    }

    return x0;
}
