package pangram

is_pangram :: proc(str: string) -> bool {
    target :: 0x3FF_FFFF

    letters := 0
    for ch in str {
        if ch >= 'a' && ch <= 'z' {
            letters |= 1 << uint(ch - 'a')
        }
        if ch >= 'A' && ch <= 'Z' {
            letters |= 1 << uint(ch - 'A')
        }
    }

    return target == letters
}
