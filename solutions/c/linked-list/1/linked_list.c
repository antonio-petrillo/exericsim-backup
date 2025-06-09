#include "linked_list.h"

struct list_node {
  struct list_node *prev, *next;
  ll_data_t data;
};

struct list {
  struct list_node *first, *last;
  int size;
};

struct list *list_create(void) {
  struct list *list = (struct list *)malloc(sizeof(struct list));
  list->size = 0;
  list->first = list->last = NULL;
  return list;
}

static struct list_node *make_node(ll_data_t data, struct list_node *prev,
                                   struct list_node *next) {
  struct list_node *node = (struct list_node *)malloc(sizeof(struct list_node));
  node->next = next;
  node->prev = prev;
  node->data = data;
  return node;
}

size_t list_count(const struct list *list) { return list->size; }

void list_push(struct list *list, ll_data_t item_data) {
  if (list->last == NULL) {
    struct list_node *node = make_node(item_data, NULL, NULL);
    list->last = node;
    list->first = node;
    list->size = 1;
  } else {
    struct list_node *node = make_node(item_data, list->last, NULL);
    list->last->next = node;
    list->last = node;
    list->size++;
  }
}

ll_data_t list_pop(struct list *list) {
  ll_data_t data = list->last->data;
  struct list_node *tmp = list->last;
  if (list->size == 1) {
    list->last = list->first = NULL;
  } else {
    list->last = tmp->prev;
    list->last->next = NULL;
    tmp->prev = NULL;
  }
  free(tmp);
  list->size--;
  return data;
}

void list_unshift(struct list *list, ll_data_t item_data) {
  if (list->first == NULL) {
    struct list_node *node = make_node(item_data, NULL, NULL);
    list->last = node;
    list->first = node;
    list->size = 1;
  } else {
    struct list_node *node = make_node(item_data, NULL, list->first);
    list->first->prev = node;
    list->first = node;
    list->size++;
  }
}

ll_data_t list_shift(struct list *list) {
  ll_data_t data = list->first->data;
  struct list_node *tmp = list->first;
  if (list->size == 1) {
    list->last = list->first = NULL;
  } else {
    list->first = tmp->next;
    list->first->prev = NULL;
    tmp->next = NULL;
  }
  free(tmp);
  list->size--;
  return data;
}

void list_delete(struct list *list, ll_data_t data) {
  struct list_node *iter = list->first;
  while (iter && iter->data != data) {
    iter = iter->next;
  }
  if (iter) {
    struct list_node *tmp = iter;
    if (list->size == 1) {
      list->first = list->last = NULL;
    } else if (iter->prev == NULL) {
      list->first = iter->next;
      list->first->prev = NULL;
    } else if (iter->next == NULL) {
      list->last = iter->prev;
      iter->prev->next = NULL;
    } else {
      iter->prev->next = iter->next;
      iter->next->prev = iter->prev;
    }
    list->size--;
    free(tmp);
  }
}

void list_destroy(struct list *list) {
  struct list_node *iter = list->first, *tmp = NULL;
  while (iter) {
    tmp = iter;
    iter = iter->next;
    free(tmp);
  }
  free(list);
  list = NULL;
}
