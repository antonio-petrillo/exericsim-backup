package eliuds_eggs

import "base:intrinsics"

egg_count :: proc(number: uint) -> uint {
    /* count := 0 */
    /* n := number */

    /* for n != 0 { */
    /*     count += 1 */
    /*     n &= (n - 1) */
    /* } */

    return intrinsics.count_ones(number)
}
