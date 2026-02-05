package intergalactic_transmission

import "base:intrinsics"

@(rodata, private="file")
ENCODE_MASKS := [?]u8{0xFE, 0xFC, 0xF8, 0xF0, 0xE0, 0xC0, 0x80}

transmit_sequence :: proc(msg: []u8) -> (seq: []u8) {
    if len(msg) == 0 { return nil }
    encoded := make([dynamic]u8)

    curr: u8
    overflow: u8
    has_overflow: bool

    for ch, i in msg {
        i_mod_7 := u8(i % len(ENCODE_MASKS))
        mask := ENCODE_MASKS[i_mod_7]

        curr = (ch & mask) >> i_mod_7
        curr |= overflow
        overflow = (ch &~ mask) << (7 - i_mod_7)

        append(&encoded, add_parity_bit(curr))

        if i_mod_7 == len(ENCODE_MASKS) - 1{
            append(&encoded, add_parity_bit(overflow))
            overflow = 0
            has_overflow = false
        } else {
            has_overflow = true
        }

    }

    if has_overflow {
        append(&encoded, add_parity_bit(overflow))
    }

    shrink(&encoded)
    return encoded[:]
}

Parity :: enum { Even, Odd }

get_parity_bit :: proc(b: u8) -> Parity {
    return intrinsics.count_ones(b) & 1 == 0 ? .Even : .Odd
}

add_parity_bit :: proc(b: u8) -> u8 {
    if get_parity_bit(b) == .Odd {
       return b | 1
    }
    return b
}

@(rodata, private="file")
DECODE_MASKS_CURR     := [?]u8{0x7F, 0x40, 0x60, 0x70, 0x78, 0x7C, 0x7E, 0x7F}
@(rodata, private="file")
DECODE_MASKS_OVERFLOW := [?]u8{0x00, 0x3F, 0x1F, 0x0F, 0x07, 0x03, 0x01, 0x00}

decode_message :: proc(seq: []u8) -> (msg: []u8, okay: bool) {
    decoded := make([dynamic]u8)

    prev: u8
    first := true
    for ch, i in seq {
        if get_parity_bit(ch) == .Odd {
            delete(decoded)
            return nil, false
        }

        bits := (ch & 0xfe) >> 1
        if first {
            first = false
            prev = bits << 1
            continue
        }
        i_mod_8 := u8(i % len(DECODE_MASKS_CURR))
        mask := DECODE_MASKS_CURR[i_mod_8]
        shift := u8(7 - i_mod_8)
        curr := ((bits & mask) >> shift) | prev

        prev = (bits & DECODE_MASKS_OVERFLOW[i_mod_8]) << (i_mod_8 + 1)

        append(&decoded, curr)

        if i_mod_8 == len(DECODE_MASKS_CURR) - 1 {
            first = true
        }
    }

    shrink(&decoded)
    return decoded[:], true
}
