const std = @import("std");

pub const Coordinate = struct {
    x: f32,
    y: f32,

    pub fn init(x_coord: f32, y_coord: f32) Coordinate {
        return .{
            .x = x_coord,
            .y = y_coord,
        };
    }
    pub fn score(self: Coordinate) usize {
        const dist = std.math.sqrt(self.x * self.x + self.y * self.y);
        return if (dist <= 1.0) 10 else if (dist <= 5.0) 5 else if (dist <= 10.0) 1 else 0;
    }
};
