package list_ops


// Returns a new list with 'other' appended to 'l'.
ls_append :: proc(l: []$T, other: []T) -> []T {
    new_list := make([]T, len(l) + len(other))
    for &el, i in l {
        new_list[i] = el
    }
    offset := len(l)
    for &el, i in other {
        new_list[offset + i] = el
    }

    return new_list
}

ls_concat :: proc(lists: [][]$T) -> []T {
    size := 0
    for l in lists {
        size += len(l)
    }
    new_list := make([]T, size)
    offset := 0
    for l in lists {
        for &el, i in l {
            new_list[offset + i] = el
        }
        offset += len(l)
    }
    return new_list
}

ls_filter :: proc(l: []$T, pred: proc(element: T) -> bool) -> []T {
    new_list := make([dynamic]T)
    for el in l {
        if pred(el) {
            append(&new_list, el)
        }
    }
    shrink(&new_list)
    return new_list[:]
}

ls_length :: proc(l: []$T) -> int {
    return len(l)
}

ls_map :: proc(l: []$T, transform: proc(element: T) -> $U) -> []U {
    out := make([]U, len(l))
    for &el, i in l {
        out[i] = transform(el)
    }
    return out
}

ls_foldl :: proc(l: []$T, initial_value: T, fn: proc(acc, element: T) -> $U) -> U {
    acc := initial_value
    for el in l {
        acc = fn(acc, el)
    }
    return acc
}

ls_foldr :: proc(l: []$T, initial_value: T, fn: proc(acc, element: T) -> $U) -> U {
    acc := initial_value
    #reverse for el in l {
        acc = fn(acc, el)
    }
    return acc
}

ls_reverse :: proc(l: []$T) -> []T {
    size := len(l)
    rev := make([]T, size)
    for &el, i in l {
        rev[size - i - 1] = el
    }
    return rev
}
