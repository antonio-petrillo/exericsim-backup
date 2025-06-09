#include "prime_factors.h"

size_t find_factors(uint64_t n, uint64_t factors[static MAXFACTORS]) {
  size_t count = 0;
  while (!(n & 1)) {
      n >>= 1;
      factors[count++] = 2;
  }
  uint64_t factor = 3;
  while(n != 1) {
      if (n % factor == 0) {
          n /= factor;
          factors[count++] = factor;
      } else {
          factor += 2;
      }
  } 
  return count;
}