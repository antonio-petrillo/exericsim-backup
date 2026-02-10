package binary_search_tree

Tree :: ^Node

Node :: struct {
	value:  int,
	height: int,
	left:   Tree,
	right:  Tree,
}

destroy_tree :: proc(t: Tree) {
	destroy_node(t)
}

destroy_node :: proc(root: ^Node) {
	if root != nil {
		destroy_node(root.left)
		destroy_node(root.right)
		free(root)
	}
}

insert :: proc(t: ^Tree, value: int) {
	t^ = insert_rec(t^, value)
}

insert_rec :: proc(root: ^Node, value: int) -> ^Node {
	if root == nil {
		new_node := new(Node)
		new_node^ = {
			value  = value,
			height = 1,
		}
		return new_node
	}

	if root.value < value {
		root.right = insert_rec(root.right, value)
	} else {
		root.left = insert_rec(root.left, value)
	}
	return fixup(root)
}

height :: proc(root: ^Node) -> int {
	return 0 if root == nil else root.height
}

clockwise_left :: proc(root: ^Node) -> ^Node {
	pivot := root.right
	root.right = pivot.left
	pivot.left = root
	pivot.height = max(height(pivot.left), height(pivot.right)) + 1
	root.height = max(height(root.left), height(root.right))
	return pivot
}

clockwise_right :: proc(root: ^Node) -> ^Node {
	pivot := root.left
	root.left = pivot.right
	pivot.right = root
	pivot.height = max(height(pivot.left), height(pivot.right)) + 1
	root.height = max(height(root.left), height(root.right))
	return pivot
}

double_clockwise_left :: proc(root: ^Node) -> ^Node {
	root.right = clockwise_right(root.right)
	return clockwise_left(root)
}

double_clockwise_right :: proc(root: ^Node) -> ^Node {
	root.left = clockwise_left(root.left)
	return clockwise_right(root)
}

fixup :: proc(root: ^Node) -> ^Node {
	assert(root != nil)
	h_left, h_right := height(root.left), height(root.right)
	if abs(h_left - h_right) <= 1 {
		return root
	}

	if h_left > h_right { 	// fixup left
		if height(root.left.left) > height(root.left.right) {
			return clockwise_left(root)
		} else {
			return double_clockwise_left(root)
		}
	} else { 	// fixup right
		if height(root.right.right) > height(root.right.left) {
			return clockwise_right(root)
		} else {
			return double_clockwise_right(root)
		}
	}
}

sorted_data :: proc(t: Tree) -> []int {
	acc := make([dynamic]int)
	sorted_data_rec(t, &acc)
	return acc[:]
}

sorted_data_rec :: proc(root: ^Node, acc: ^[dynamic]int) {
	if root != nil {
		sorted_data_rec(root.left, acc)
		append(acc, root.value)
		sorted_data_rec(root.right, acc)
	}
}
