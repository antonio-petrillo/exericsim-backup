#include "matching_brackets.h"

stack push(stack top, char value) {
  stack newstack = (stack)malloc(sizeof(struct node));
  if (!newstack) {
    exit(-1);
  }
  newstack->next = top;
  newstack->value = value;
  return newstack;
}

char pop(stack *top) {
  if (!(*top)) {
    exit(-1);
  }
  stack tmp = (*top);
  (*top) = (*top)->next;
  char value = tmp->value;
  free(tmp);
  return value;
}

bool is_empty(stack top) { return top == NULL; }

bool is_paired(const char *input) {
  int index = 0;
  stack s = NULL;
  while (input[index] != '\0') {
    char current = input[index];
    if (current == '(' || current == '[' || current == '{') {
      s = push(s, current);
    } else if (current == ')' || current == ']' || current == '}') {
      if (is_empty(s)) {
        while (!is_empty(s)) {
          pop(&s);
        }
        return false;
      }
      char topped = pop(&s);
      if (topped != get_corresponding(current)) {
        while (!is_empty(s)) {
          pop(&s);
        }
        return false;
      }
    }
    index++;
  }
  bool result = is_empty(s);
  while (!is_empty(s)) {
    pop(&s);
  }
  return result;
}

char get_corresponding(char current) {
  if (current == ')')
    return '(';
  else if (current == ']')
    return '[';
  else
    return '{';
}
