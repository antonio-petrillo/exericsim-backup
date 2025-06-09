#include "circular_buffer.h"
#include <stdlib.h>

circular_buffer_ptr new_circular_buffer(int length) {
  circular_buffer_ptr buffer =
      (circular_buffer_ptr)malloc(sizeof(struct circular_buffer));
  if (!buffer)
    return NULL;
  buffer->capacity = buffer->index_pop = buffer->index_push = 0;
  buffer->length = length;
  buffer->values = (buffer_value_t *)calloc(length, sizeof(buffer_value_t));
  if (!buffer->values) {
    free(buffer);
    buffer = NULL;
  }

  return buffer;
}

static int16_t internal_write(circular_buffer_ptr buffer, buffer_value_t value,
                              bool overwrite) {
  if (!overwrite && buffer->capacity == buffer->length) {
    errno = ENOBUFS;
    return EXIT_FAILURE;
  }
  buffer->values[buffer->index_push] = value;
  if (overwrite && buffer->index_push == buffer->index_pop) {
    buffer->index_pop = (buffer->index_pop + 1) % buffer->length;
  }
  buffer->index_push = (buffer->index_push + 1) % buffer->length;
  buffer->capacity = buffer->capacity == buffer->length ? buffer->capacity
                                                        : buffer->capacity + 1;
  return EXIT_SUCCESS;
}

int16_t write(circular_buffer_ptr buffer, buffer_value_t value) {
  return internal_write(buffer, value, false);
}

int16_t overwrite(circular_buffer_ptr buffer, buffer_value_t value) {
  return internal_write(buffer, value, true);
}

int16_t read(circular_buffer_ptr buffer, buffer_value_t *value) {
  if (buffer->capacity == 0) {
    errno = ENODATA;
    return EXIT_FAILURE;
  }
  *value = buffer->values[buffer->index_pop];
  buffer->index_pop = (buffer->index_pop + 1) % buffer->length;
  buffer->capacity--;
  return EXIT_SUCCESS;
}

void clear_buffer(circular_buffer_ptr buffer) {
  buffer->index_push = buffer->index_pop = buffer->capacity = 0;
}

void delete_buffer(circular_buffer_ptr buffer) {
  free(buffer->values);
  free(buffer);
  buffer = NULL;
}
