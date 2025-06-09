#include "eliuds_eggs.h"

unsigned int egg_count(unsigned int n){
    unsigned int result = 0;
    while(n != 0) {
        result++;
        n &= n - 1;
    }
    return result;
}