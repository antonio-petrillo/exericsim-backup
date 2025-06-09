pub fn binarySearch(comptime T: type, target: usize, items: []const T) ?usize {
    var low: usize = 0;
    var high: usize = items.len;
    var medium: usize = (low + high) >> 1;

    while (low < high) {
        if (items[medium] < target) {
            low = medium;
        } else if (items[medium] > target) {
            high = medium;
        } else {
            return medium;
        }
        medium = (low + high) >> 1;
    }

    return null;
}
