package raindrops

import "core:strings"

convert :: proc(number: int) -> string {
    sb := strings.builder_make()
    defer strings.builder_destroy(&sb)
    if number % 3 == 0 {
        strings.write_string(&sb, "Pling")
    }
    if number % 5 == 0 {
        strings.write_string(&sb, "Plang")
    }
    if number % 7 == 0 {
        strings.write_string(&sb, "Plong")
    }
    if len(sb.buf) == 0 {
        strings.write_int(&sb, number, 10)
    }

    return strings.to_string(sb)[:]
}
