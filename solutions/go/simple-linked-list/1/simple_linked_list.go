package linkedlist

import (
	"errors"
)

var ErrEmptyList = errors.New("Empty List")

type node struct {
	next  *node
	value int
}

type List struct {
	size int
	head *node
}

func New(elements []int) *List {
	l := &List{
		size: 0,
		head: nil,
	}

	for _, el := range elements {
		l.Push(el)
	}

	return l
}

func (l *List) Size() int {
	return l.size
}

func (l *List) Push(element int) {
	l.size++
	n := &node{
		value: element,
		next:  l.head,
	}
	l.head = n
}

func (l *List) Pop() (int, error) {
	if l.size == 0 {
		return 0, ErrEmptyList
	}
	l.size--
	n := l.head
	l.head = l.head.next
	n.next = nil
	return n.value, nil
}

func (l *List) Array() []int {
	var arr []int

	for n := l.head; n != nil; n = n.next {
		arr = append(arr, n.value)
	}

	size := len(arr)

	for i := 0; i < size>>1; i++ {
		arr[i], arr[size-1-i] = arr[size-1-i], arr[i]
	}

	return arr
}

func (l *List) Reverse() *List {
	rev := New([]int{})
	for n := l.head; n != nil; n = n.next {
		rev.Push(n.value)
	}
	return rev
}
