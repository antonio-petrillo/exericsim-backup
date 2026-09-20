#include "rna_transcription.h"
#include <string.h>
#include <stdlib.h>


char *to_rna(const char *dna) {
    size_t size = strlen(dna);
    char *rna = calloc(size + 1, sizeof(char));
    for (size_t i = 0; i < size; i++) {
        char ch;
        switch (dna[i]) {
            case 'A': ch = 'U'; break;
            case 'T': ch = 'A'; break;
            case 'C': ch = 'G'; break;
            case 'G': ch = 'C'; break;
        }
        rna[i] = ch;
    }
    return rna;
}