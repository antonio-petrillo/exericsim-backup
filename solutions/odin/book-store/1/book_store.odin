package book_store

import "core:slice"

@(rodata)
DISCOUNTS := [5]u32{3000, 2560, 2160, 1520, 800}

total :: proc(books: []u32) -> (tot: u32) {
    if len(books) == 0 { return 0 }

    freqs: [5]u32
    for book in books {
        freqs[book - 1] += 1
    }

    slice.sort(freqs[:])
    basket := [5]u32{freqs[0], 0, 0, 0, 0}
    for i in 1..<5 {
        basket[i] = freqs[i] - freqs[i - 1]
    }

    count := min(basket[0], basket[2])
    if count > 0 {
        basket[1] += count << 1
        basket[0] -= count
        basket[2] -= count
    }

    for cost in basket * DISCOUNTS {
        tot += cost
    }
    return
}
