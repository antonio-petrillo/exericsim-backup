package binarysearchtree

type BinarySearchTree struct {
	left  *BinarySearchTree
	data  int
	right *BinarySearchTree
}

func NewBst(i int) *BinarySearchTree {
	return &BinarySearchTree{
        left: nil,
        right: nil,
        data: i,
    }
}

func (bst *BinarySearchTree) Insert(i int) {
	bst = insert(bst, i)
}

func insert(bst *BinarySearchTree, i int) *BinarySearchTree {
    if bst == nil {
        return NewBst(i)
    }
    if bst.data >= i {
        bst.left = insert(bst.left, i)
    } else {
       	bst.right = insert(bst.right, i)
    }
    return bst
}

// SortedData returns the ordered contents of BinarySearchTree as an []int.
// The values are in increasing order starting with the lowest int value.
// A BinarySearchTree that has the numbers [1,3,7,5] added will return the
// []int [1,3,5,7].
func (bst *BinarySearchTree) SortedData() (out []int) {
    return visit(bst, out)
}

func visit(bst *BinarySearchTree, output []int) []int {
    if bst == nil {
        return output
    }
    output = visit(bst.left, output)
    output = append(output, bst.data)
    output = visit(bst.right, output)
    return output
}
