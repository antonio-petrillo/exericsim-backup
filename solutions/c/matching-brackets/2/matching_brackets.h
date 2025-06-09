#ifndef MATCHING_BRACKETS_H
#define MATCHING_BRACKETS_H

#include <stdbool.h>
#include <stdlib.h>

typedef struct node {
  struct node *next;
  char value;
} * stack;

stack push(stack top, char value);
char pop(stack *top);
bool is_empty(stack top);

char get_corresponding(char current);

bool is_paired(const char *input);

#endif
