package sieve

import "core:container/bit_array"

primes :: proc(limit: int) -> []int {
    ba := bit_array.create(limit + 1)
    defer bit_array.destroy(ba)

    for i in 2..=limit {
        bit_array.unsafe_set(ba, i)
    }

    for i in 2..=limit {
        if bit_array.unsafe_get(ba, i) {
            for j := i << 1; j <= limit; j += i {
                bit_array.unset(ba, j)
            }
        }
    }

    res := make([dynamic]int)
    iter := bit_array.Bit_Array_Iterator{
        array = ba,
    }

    for prime in bit_array.iterate_by_set(&iter) {
        append(&res, prime)
    }

    shrink(&res)
    return res[:]
}
