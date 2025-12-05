#include "eliuds_eggs.h"

namespace chicken_coop {

int positions_to_quantity(int pos) {
    int count {};
    while (pos != 0) {
        count++;
        pos &= pos - 1;
    }
    return count;
}

}  // namespace chicken_coop
