pub fn binarySearch(comptime T: type, target: usize, items: []const T) ?usize {
    var low: usize = 0;
    var high: usize = items.len;

    while (low < high) {
        const medium: usize = (low + high) >> 1;
        if (items[medium] < target) {
            low = medium + 1;
        } else if (items[medium] > target) {
            high = medium;
        } else {
            return medium;
        }
    }

    return null;
}
