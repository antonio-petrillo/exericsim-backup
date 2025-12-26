#include "trinary.h"

namespace trinary {

// TODO: add your solution here
int to_decimal(std::string s) {
    int decimal{};
    for(auto ch : s) {
        if (ch < '0' || ch > '2') return 0;
        decimal = decimal * 3 + (ch - '0');
    } 
    return decimal;
}

}  // namespace trinary
