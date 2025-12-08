#include "armstrong_numbers.h"
#include <vector>

namespace armstrong_numbers {

namespace {
    int ipow(int base, int exp) {
        int result = 1;
        while (true) {
            if (exp & 1) result *= base;
            exp >>= 1;
            if (!exp) break;
            base *= base;
        }
        return result;
    }
}
    
bool is_armstrong_number(int num) {
    std::vector<int> v;

    int copy {num};
    int pow {};

    while (copy != 0) {
        v.push_back(copy % 10);
        copy /= 10;
        pow++;
    }

    int sum{};

    for (int digit : v) {
        sum += ipow(digit, pow);
    }
    
    return sum == num;
}

}  // namespace armstrong_numbers
