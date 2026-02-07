package zipper

// A Binary Tree representation
Tree :: ^Node

// A single Node within the Binary Tree
Node :: struct {
	value: int,
	left:  Tree,
	right: Tree,
}

// The Zipper data structure that keeps track of the focus and the previous operations.
Zipper :: struct {
	tree:  Tree,
	trail: Trail,
}

// The set of previous operations applied to the tree.
Trail :: ^Step

// A single operation applied to the tree.
Step :: struct {
	action: Action,
	value:  int,
	tree:   Tree,
	next:   Trail,
}

// The possible operations applied to the tree.
Action :: enum {
	Right,
	Left,
}

// Get a zipper out of a tree, the focus is on the root node.
zip_from_tree :: proc(t: Tree) -> Zipper {
    return Zipper{tree = t}
}

// Get the tree out of the zipper.
zip_to_tree :: proc(z: Zipper) -> Tree {
    z := z
    for z.trail != nil {
        z = zip_up(z)
    }
    return z.tree
}

// Get the value of the focus node.
zip_value :: proc(z: Zipper) -> int {
    return z.tree.value if z.tree != nil else 0
}

// Move the focus to the left child of the focus node, returns a new zipper.
// If there is no left child, return a zero value Zipper.
zip_left :: proc(z: Zipper) -> Zipper {
    if z.tree == nil {
        return Zipper{}
    }

    trail := new(Step)
    trail^ = Step{
        action = .Left,
        value = z.tree.value,
        tree = z.tree.right,
        next = z.trail,
    }

    return Zipper{
        tree = z.tree.left,
        trail = trail,
    }
}

// Move the focus to the right child of the focus node, returns a new zipper.
// If there is no right child, return a zero value Zipper.
zip_right :: proc(z: Zipper) -> Zipper {
    if z.tree == nil {
        return Zipper{}
    }

    trail := new(Step)
    trail^ = Step{
        action = .Right,
        value = z.tree.value,
        tree = z.tree.left,
        next = z.trail,
    }

    return Zipper{
        tree = z.tree.right,
        trail = trail,
    }
}

// Move the focus to the parent of the focus node, returns a new zipper.
// If there is no parent, return a zero value Zipper.
zip_up :: proc(z: Zipper) -> Zipper {
    if z.trail == nil {
        return Zipper{}
    }

    tree := new(Node)
    tree.value = z.trail.value
    switch z.trail.action {
    case .Left:
        tree.left = z.tree
        tree.right = z.trail.tree
    case .Right:
        tree.right = z.tree
        tree.left = z.trail.tree
    }

    return Zipper {
        tree = tree,
        trail = z.trail.next,
    }
}

// Set the value of the focus node, returns a new zipper.
zip_set_value :: proc(z: Zipper, value: int) -> Zipper {
    tree := new(Node)
    tree^ = {
        value = value,
        left = z.tree.left,
        right = z.tree.right,
    }

    return Zipper{
        tree = tree,
        trail = z.trail,
    }
}

// Set the left subtree of the focus node, returns a new zipper.
zip_set_left :: proc(z: Zipper, subtree: Tree) -> Zipper {
    tree := new(Node)
    tree^ = {
        left = subtree,
        value = z.tree.value,
        right = z.tree.right,
    }

    return Zipper{
        tree = tree,
        trail = z.trail,
    }
}

// Set the right subtree of the focus node, returns a new zipper.
zip_set_right :: proc(z: Zipper, subtree: Tree) -> Zipper {
    tree := new(Node)
    tree^ = {
        right = subtree,
        value = z.tree.value,
        left = z.tree.left,
    }

    return Zipper{
        tree = tree,
        trail = z.trail,
    }
}
