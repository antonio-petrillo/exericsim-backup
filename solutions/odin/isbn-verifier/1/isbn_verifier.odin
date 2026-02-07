package isbn_verifier

is_valid :: proc(isbn: string) -> bool {
    index := 0
    digits: [10]int

    for ch in transmute([]u8)isbn {
        if index > 9 { return false }

        if ch == ' ' || ch == '-' { continue }
        else if index == 9 && ch == 'X' {
           digits[9] = 10
        } else if ch >= '0' && ch <= '9' {
            digits[index] = int(ch - '0')
        } else {
         return false
        }
        index += 1
    }

    if  index != 10 { return false }

    sum := 0
    for digit, i in digits {
        sum += digit * (10 - i)
    }

    return sum % 11 == 0
}
