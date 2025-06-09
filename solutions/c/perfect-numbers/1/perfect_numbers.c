#include "perfect_numbers.h"

kind classify_number(int number) {
    if (number <= 0)
        return ERROR;

    int sum = 0;
    for (int divisor = 1; divisor << 1 <= number; divisor++) {
        if (number % divisor == 0) {
            sum += divisor;
        }
    }

    if (sum == number) {
        return PERFECT_NUMBER;
    } else if (sum > number) {
        return ABUNDANT_NUMBER;
    } else {
       return DEFICIENT_NUMBER;
    }
    
}