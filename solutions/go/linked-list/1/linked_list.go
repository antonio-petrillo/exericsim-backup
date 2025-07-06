package linkedlist

import "errors"

var errEmptyList error = errors.New("Empty List")

type Node struct {
	Value    any
	next     *Node
	previous *Node
}

func (n *Node) Next() *Node {
	return n.next
}

func (n *Node) Prev() *Node {
	return n.previous
}

type List struct {
	head *Node
	tail *Node
}

func NewList(elements ...any) *List {
	l := &List{}
	for _, el := range elements {
		l.Push(el)
	}
	return l
}

func (l *List) Unshift(v any) {
	n := &Node{Value: v}
	if l.head == nil {
		l.head, l.tail = n, n
		return
	}
	n.next, l.head.previous, l.head = l.head, n, n
}

func (l *List) Push(v any) {
	n := &Node{Value: v}
	if l.tail == nil {
		l.head, l.tail = n, n
		return
	}
	n.previous, l.tail.next, l.tail = l.tail, n, n
}

func (l *List) Shift() (any, error) {
	if l.head == nil {
		return nil, errEmptyList
	}
	n := l.head

	if l.head == l.tail {
		l.head, l.tail = nil, nil
	} else {
		l.head.next.previous, l.head = nil, n.next
	}
	return n.Value, nil
}

func (l *List) Pop() (any, error) {
	if l.head == nil {
		return nil, errEmptyList
	}
	n := l.tail
	if l.tail == l.head {
		l.head, l.tail = nil, nil
	} else {
		l.tail.previous.next, l.tail = nil, n.previous
	}
	return n.Value, nil
}

func (l *List) Reverse() {
	l.head, l.tail = l.tail, l.head
	for iter := l.head; iter != nil; iter = iter.next {
		iter.next, iter.previous = iter.previous, iter.next
	}
}

func (l *List) First() *Node {
	return l.head
}

func (l *List) Last() *Node {
	return l.tail
}
