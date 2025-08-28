#include "binary_search_tree.h"
#include <stdlib.h>

static node_t *add_node(node_t *root, int data) {
  if (root == NULL) {
    root = (node_t*) malloc(sizeof(*root)); 
    // not handling out of mem error
    root->right = root->left = NULL;
    root->data = data;

    return root;
  }

  if(root->data >= data) {
    root->left = add_node(root->left, data);
  } else {
    root->right = add_node(root->right, data);
  }
  return root;
}

node_t *build_tree(int *tree_data, size_t tree_data_len) {
  node_t *root = NULL;
  for(size_t i = 0; i < tree_data_len; i++) {
    root = add_node(root, tree_data[i]);
  }
  return root;
}

void free_tree(node_t *tree) {
  if (tree) {
    free_tree(tree->left);
    free_tree(tree->right);
    free(tree);
  }
}

static int required_size(node_t *tree) {
  int left = tree->left ? required_size(tree->left) : 0;
  int right = tree->right ? required_size(tree->right) : 0;
  
  return left + right + 1;
}

static void sort_data_insert(node_t *tree, int *array, int *index) {
  if(!tree) return;
  sort_data_insert(tree->left, array, index);
  array[(*index)++] = tree->data;
  sort_data_insert(tree->right, array, index);
}

int *sorted_data(node_t *tree) {
  int size = required_size(tree);
  if (!size) return NULL;
  int* sorted = (int*) calloc(size, sizeof(int));
  int index = 0;
  sort_data_insert(tree, sorted, &index);
  return sorted;
}
