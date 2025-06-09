#include "pangram.h"

bool is_pangram(const char *sentence) {
    if (!sentence) {
        return false;
    }
    size_t checksum = 0;
    for (size_t i = 0; i < strlen(sentence); i++) {
        if (isalpha(sentence[i])) {        // to avoid not letters
            char c = tolower(sentence[i]); // A -> a
            checksum |= 1 << (c - 'a');
        }
    }
    return checksum == 0x3ffffff;
}
