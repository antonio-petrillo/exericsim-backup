#ifndef CIRCULAR_BUFFER_H
#define CIRCULAR_BUFFER_H

#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>

typedef int buffer_value_t;

typedef struct circular_buffer {
  buffer_value_t *values;
  int length;
  int capacity;
  int index_pop;
  int index_push;
} circular_buffer_t;

typedef circular_buffer_t *circular_buffer_ptr;

circular_buffer_ptr new_circular_buffer(int length);
int16_t write(circular_buffer_ptr buffer, buffer_value_t value);
int16_t overwrite(circular_buffer_ptr buffer, buffer_value_t value);
int16_t read(circular_buffer_ptr buffer, buffer_value_t *value);
void clear_buffer(circular_buffer_ptr buffer);
void delete_buffer(circular_buffer_ptr buffer);

#endif
