pub fn eggCount(number: usize) usize {
    var count: usize = 0;
    var n = number;
    while (n != 0) : (n = n & (n - 1)) {
        count = count + 1;
    }
    return count;
}
