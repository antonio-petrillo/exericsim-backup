#include "binary_search.h"

const int *binary_search(int value, const int *arr, size_t length) {
    if (length == 0 || value > arr[length - 1] || value < arr[0])
        return NULL;
    size_t min = 0, max = length - 1;
    while (min <= max) {
        size_t middle = (min + max) >> 1;
        if (arr[middle] > value)
            max = middle - 1;
        else if (arr[middle] < value)
            min = middle + 1;
        else
            return &arr[middle];
    }
    return NULL;
}
