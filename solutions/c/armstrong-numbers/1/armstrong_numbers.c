#include "armstrong_numbers.h"

bool is_armstrong_number(int candidate) {
  int num_of_digit = count_digits(candidate);
  int sum = 0;
  int copy = candidate;
  while (copy > 0) {
    int digit = copy % 10;
    copy /= 10;
    sum += mypow(digit, num_of_digit);
  }

  return sum == candidate;
}

int count_digits(int number) {
  int digits = 0;
  while (number > 0) {
    number /= 10;
    digits++;
  }
  return digits;
}

int mypow(int base, int pow) {
  int result = 1;
  while (pow > 0) {
    result *= base;
    pow--;
  }
  return result;
}
