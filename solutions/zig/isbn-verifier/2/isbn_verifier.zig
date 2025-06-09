pub fn isValidIsbn10(s: []const u8) bool {
    var sum: u32 = 0;
    var counter: u32 = 0;

    for (s) |digit| {
        if (counter > 9) {
            return false;
        }
        if (counter == 9 and digit == 'X') {
            sum += 10;
            counter += 1;
        } else if (digit >= 48 and digit <= 57) {
            sum += (digit - '0') * (10 - counter);
            counter += 1;
        } else if (digit != '-') {
            return false;
        }
    }
    return counter == 10 and sum % 11 == 0;
}
