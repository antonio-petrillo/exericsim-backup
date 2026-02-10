package line_up

import "core:fmt"
import "core:strings"

Suffix :: enum {
    First,
    Second,
    Third,
    Nth,
}

suffix_to_string :: proc(suffix: Suffix) -> (s: string) {
    switch suffix {
    case .First: s = "st"
    case .Second: s = "nd"
    case .Third: s = "rd"
    case .Nth: s = "th"
    }
    return
}

format :: proc(name: string, number: int) -> string {
    sb := strings.builder_make()

    suf: Suffix
    last, butlast_is_not_one := number % 10, number / 10 % 10 != 1
    switch last {
    case 1: suf = .First if butlast_is_not_one else .Nth
    case 2: suf = .Second if butlast_is_not_one else .Nth
    case 3: suf = .Third if butlast_is_not_one else .Nth
    case: suf = .Nth
    }

    fmt.sbprintf(&sb, "%s, you are the %d%s customer we serve today. Thank you!", name, number, suffix_to_string(suf))

    s := strings.to_string(sb)
    defer strings.builder_destroy(&sb)

    return s[:]
}
