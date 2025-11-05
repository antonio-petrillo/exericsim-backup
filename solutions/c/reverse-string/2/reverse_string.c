#include "reverse_string.h"

#include <stddef.h>
#include <stdlib.h>

char *reverse(const char *value) {
    size_t len = 0;
    for (const char *ch = value; *ch != '\0'; ch++) {
        len++;
    }
    char *reversed = calloc(len + 1, sizeof(char));
    if (!reversed) return NULL;
    char *from_end = reversed + len - 1;
    for (const char *ch = value; *ch != '\0'; ch++) {
        *from_end = *ch;
        from_end--;
    }
    return reversed;
}




#ifndef REVERSE_STRING_H
#define REVERSE_STRING_H

char *reverse(const char *value);

#endif
