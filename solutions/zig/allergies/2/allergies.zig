const std = @import("std");
const EnumSet = std.EnumSet;

pub const Allergen = enum(u3) {
    eggs,
    peanuts,
    shellfish,
    strawberries,
    tomatoes,
    chocolate,
    pollen,
    cats,
};

pub fn isAllergicTo(score: u8, allergen: Allergen) bool {
    return score & @shlExact(@as(u8, 1), @intFromEnum(allergen)) != 0;
}

pub fn initAllergenSet(score: usize) EnumSet(Allergen) {
    var set = EnumSet(Allergen).init(.{});
    const allergens = [8]Allergen{ .eggs, .peanuts, .shellfish, .strawberries, .tomatoes, .chocolate, .pollen, .cats };
    for (allergens) |allergen| {
        if (score & (@as(usize, 1) << @intFromEnum(allergen)) != 0) {
            set.insert(allergen);
        }
    }
    return set;
}
