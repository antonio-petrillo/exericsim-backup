package roman_numerals

import "core:strings"

to_roman_numeral :: proc(decimal: int) -> string {
    num := decimal
    sb := strings.builder_make()
    for num != 0{
        switch {
        case num >= 1000:
            strings.write_string(&sb, "M")
            num -= 1000
        case num >= 900:
            strings.write_string(&sb, "CM")
            num -= 900
        case num >= 500:
            strings.write_string(&sb, "D")
            num -= 500
        case num >= 400:
            strings.write_string(&sb, "CD")
            num -= 400
        case num >= 100:
            strings.write_string(&sb, "C")
            num -= 100
        case num >= 90:
            strings.write_string(&sb, "XC")
            num -= 90
        case num >= 50:
            strings.write_string(&sb, "L")
            num -= 50
        case num >= 40:
            strings.write_string(&sb, "XL")
            num -= 40
        case num >= 10:
            strings.write_string(&sb, "X")
            num -= 10
        case num >= 9:
            strings.write_string(&sb, "IX")
            num -= 9
        case num >= 5:
            strings.write_string(&sb, "V")
            num -= 5
        case num >= 4:
            strings.write_string(&sb, "IV")
            num -= 4
            case:
            strings.write_string(&sb, "I")
            num -= 1
        }
    }
    return strings.to_string(sb)
}
