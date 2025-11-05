#include "binary.h"

int convert(const char *input) {
  int converted = 0;

  for (const char *c = input; c[0] ;c++) {
    if (c[0] != '0' && c[0] != '1')
      return INVALID;
    converted = (converted << 1) | (*c - '0');
  }

  return converted;
}
