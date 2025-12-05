#include "darts.h"

namespace darts {

int score(double x, double y) {
    double dist {x * x + y * y};
    int score = 0;
    
    if (dist <= 1) score = 10;
    else if (dist <= 25) score = 5;
    else if (dist <= 100) score = 1;
    
    return score;
}

}  // namespace darts
