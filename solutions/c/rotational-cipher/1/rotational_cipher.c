#include "rotational_cipher.h"
#include <stdlib.h>
#include <string.h>

char *rotate(const char *text, int shift_key) {
    size_t len = strlen(text);
    char *rotated = (char *)calloc(len + 1, sizeof(char));
    if (!rotated)
        return NULL;

    for (size_t i = 0; i <= len; i++) {
        char c = text[i];
        if (c >= 'a' && c <= 'z') {
            rotated[i] = (c - 'a' + shift_key) % 26 + 'a';
        } else if (c >= 'A' && c <= 'Z') {
            rotated[i] = (c - 'A' + shift_key) % 26 + 'A';
        } else {
            rotated[i] = c;
        }
    }

    return rotated;
}
