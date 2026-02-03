package flatten_array

Item :: union {
	i32,
	[]Item,
}

flatten :: proc(input: Item) -> []i32 {
    flattened := make([dynamic]i32)
    flatten_rec(input, &flattened)
    return flattened[:]
}

@(private="file")
flatten_rec :: proc(input: Item, acc: ^[dynamic]i32) {
    switch variant in input {
    case i32: append(acc, variant)
    case []Item:
        for item in variant {
            flatten_rec(item, acc)
        }
    }
}
