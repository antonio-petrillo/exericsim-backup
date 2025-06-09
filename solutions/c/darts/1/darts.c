#include "darts.h"

uint8_t score(coordinate_t c) {
    float square_dist = c.x * c.x + c.y * c.y;

    if (square_dist <= 1)
        return 10;
    else if (square_dist <= 25)
        return 5;
    else if (square_dist <= 100)
        return 1;
    else
        return 0;
}
