#include "isogram.h"
#include <ctype.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>

bool is_isogram(const char phrase[]) {
  if (!phrase)
    return false;

  size_t len = strlen(phrase);
  int *count = calloc(26, sizeof(int));

  for (size_t i = 0; i < len; i++) {
    char c = phrase[i];
    if (isalpha(c)) {
      c = tolower(c);
      int index = c - 97;
      count[index]++;

      if (count[index] > 1) {
        return false;
      }
    }
  }
  return true;
}
