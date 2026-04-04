package knapsack

Item :: struct {
	weight: u32,
	value:  u32,
}

get :: proc(width, r, c: u32) -> u32 {
	return r * width + c
}

maximum_value :: proc(maximum_weight: u32, items: []Item) -> u32 {
	defer free_all(context.temp_allocator)

	table := make([]u32, (maximum_weight + 1) * u32(len(items) + 1), allocator = context.temp_allocator)
	WIDTH := u32(len(items) +1)

	for w in 1..=maximum_weight {
		for item, idx in items {
			if item.weight <= w {
				include := item.value + table[get(WIDTH, w - item.weight, u32(idx))]
				exclude := table[get(WIDTH, w, u32(idx))]
				table[get(WIDTH, w, u32(idx + 1))] = max(include, exclude)
			} else {
				table[get(WIDTH, w, u32(idx + 1))] = table[get(WIDTH, w, u32(idx))]
			}
		}
	}

	return table[get(WIDTH, maximum_weight, u32(len(items)))]
}
