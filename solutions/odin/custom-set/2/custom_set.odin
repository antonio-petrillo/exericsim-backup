package custom_set

import "core:strings"
import "core:slice"

Set :: map[int]struct{}

new_set :: proc(elements: ..int) -> Set {
    set := make(Set, len(elements))
    for el in elements {
        set[el] = struct{}{}
    }
    return set
}

destroy_set :: proc(s: ^Set) {
    delete(s^)
}

to_string :: proc(s: Set) -> string {
    keys := make([]int, len(s))
    defer delete(keys)

    {
        i := 0
        for k in s {
            keys[i] = k
            i += 1
        }
    }
    slice.sort(keys)

    sb := strings.builder_make()
    strings.write_rune(&sb, '[')
    for key, i in keys {
        strings.write_int(&sb, key)
        if i != len(keys) - 1 {
            strings.write_string(&sb, ", ")
        }
    }
    strings.write_rune(&sb, ']')

    return strings.to_string(sb)
}

is_empty :: proc(s: Set) -> bool {
    return len(s) == 0
}

contains :: proc(s: Set, element: int) -> bool {
    return element in s
}

is_subset :: proc(s: Set, other: Set) -> bool {
    if len(s) > len(other) { return false }

    for el in s {
        if el not_in other { return false }
    }

    return true
}

is_disjoint :: proc(s: Set, other: Set) -> bool {
    for el in s {
        if el in other { return false }
    }
    for el in other {
        if el in s { return false }
    }
    return true
}

equal :: proc(s: Set, other: Set) -> bool {
    if len(s) != len(other) { return false }

    for el in s {
        if el not_in other { return false }
    }

    return true
}

add :: proc(s: ^Set, elements: ..int) {
    for el in elements {
        s[el] = struct{}{}
    }
}

intersection :: proc(s: Set, other: Set) -> Set {
    res := new_set()

    for el in s {
        if el in other {
            res[el] = struct{}{}
        }
    }

    return res
}

difference :: proc(s: Set, other: Set) -> Set {
    set := new_set()
    for el in s {
        if el not_in other {
            set[el] = struct{}{}
        }
    }

    return set
}

join :: proc(s: Set, other: Set) -> Set {
    set := make(Set, max(len(s), len(other)))

    for el in s {
        set[el] = struct{}{}
    }
    for el in other {
        set[el] = struct{}{}
    }

    return set
}
