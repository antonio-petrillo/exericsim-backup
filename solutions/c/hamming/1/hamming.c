#include "hamming.h"
#include <stddef.h>
#include <string.h>

int compute(const char *lhs, const char *rhs) {
  size_t len = strlen(lhs);
  /* size_t len2 = strlen(rhs); */

  if (len != strlen(rhs)) {
    return -1;
  }

  int result = 0;

  for (size_t i = 0; i < len; i++) {
    if (lhs[i] != rhs[i])
      result++;
  }

  return result;
}
