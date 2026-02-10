package binary_search_tree

Tree :: ^Node

Node :: struct {
	value: int,
	left:  Tree,
	right: Tree,
}

destroy_tree :: proc(t: Tree) {
    destroy_node(t)
}

@(private="file")
destroy_node :: proc(root: ^Node) {
    if root != nil {
        destroy_node(root.left)
        destroy_node(root.right)
        free(root)
    }
}

insert :: proc(t: ^Tree, value: int) {
    prev: Tree
    curr := t^

    for curr != nil {
        prev = curr
        if curr.value < value {
            curr = curr.right
        } else {
            curr = curr.left
        }
    }

    new_node := new(Node)
    new_node.value = value
    if prev != nil {
        if prev.value < value {
            prev.right = new_node
        } else {
            prev.left = new_node
        }
    } else {
        t^ = new_node
    }
}

sorted_data :: proc(t: Tree) -> []int {
    acc := make([dynamic]int)
    sorted_data_rec(t, &acc)
    return acc[:]
}

@(private="file")
sorted_data_rec :: proc(root: ^Node, acc: ^[dynamic]int) {
    if root != nil {
        sorted_data_rec(root.left, acc)
        append(acc, root.value)
        sorted_data_rec(root.right, acc)
    }
}
