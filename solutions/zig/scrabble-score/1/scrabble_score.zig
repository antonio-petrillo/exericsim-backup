const std = @import("std");

const scores = [_]u32{
    1, // a
    3, // b
    3, // c
    2, // d
    1, // e
    4, // f
    2, // g
    4, // h
    1, // i
    8, // j
    5, // k
    1, // l
    3, // m
    1, // n
    1, // o
    3, // p
    10, // q
    1, // r
    1, // s
    1, // t
    1, // u
    4, // v
    4, // w
    8, // x
    4, // y
    10, // z
};

pub fn score(s: []const u8) u32 {
    var score_point: u32 = 0;
    for (s) |c| {
        score_point = score_point + switch (c) {
            'a'...'z' => scores[c - 'a'],
            'A'...'Z' => scores[c - 'A'],
            else => 0,
        };
    }
    return score_point;
}
