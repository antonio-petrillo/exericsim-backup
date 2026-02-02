package linked_list

List :: struct {
    size: int,
    head: ^Node,
    tail: ^Node,
}

Node :: struct {
    next: ^Node,
    prev: ^Node,
    elem: int
}

Error :: enum {
	None,
	Empty_List,
	Unimplemented,
}

new_list :: proc(elements: ..int) -> List {
    l := List{}
    for elem in elements {
        push(&l, elem)
    }
    return l
}

destroy_list :: proc(l: ^List) {
    tmp: ^Node
    iter := l.head
    for iter != nil {
        tmp = iter
        iter = iter.next
        free(tmp)
    }
    l.head, l.tail, l.size = nil, nil, 0
}

unshift_node :: proc(l: ^List, node: ^Node) {
    node.next = l.head
    if node.next == nil {
        l.tail = node
    } else {
        node.next.prev = node
    }
    l.head = node
    l.size += 1
}

unshift_value :: proc(l: ^List, value: int) {
    node := new(Node)
    node.elem = value
    unshift_node(l, node)
}

unshift :: proc{unshift_node, unshift_value}

push_node :: proc(l: ^List, node: ^Node) {
    node.prev = l.tail
    if node.prev == nil {
        l.head = node
    } else {
        node.prev.next = node
    }
    l.tail = node
    l.size += 1
}

push_value :: proc(l: ^List, value: int) {
    node := new(Node)
    node.elem = value
    push_node(l, node)
}

push :: proc{push_value, push_node}

shift_node :: proc(l: ^List) -> (^Node, Error) {
    if l.size == 0 {
        return nil, .Empty_List
    }
    node := l.head
    if node.next == nil {
        l.head, l.tail = nil, nil
    } else {
        node.next.prev = nil
        l.head = node.next
    }
    l.size -= 1
    node.prev, node.next = nil, nil
    return node, .None
}

shift :: proc(l: ^List) -> (elem: int, err: Error) {
    node := shift_node(l) or_return
    elem = node.elem
    free(node)
    return elem, .None
}

pop_node :: proc(l: ^List) -> (^Node, Error) {
    if l.size == 0 {
        return nil, .Empty_List
    }
    node := l.tail
    if node.prev == nil {
        l.head, l.tail = nil, nil
    } else {
        node.prev.next = nil
        l.tail = node.prev
    }
    l.size -= 1
    node.prev, node.next = nil, nil
    return node, .None
}

pop :: proc(l: ^List) -> (elem: int, err: Error) {
    node := pop_node(l) or_return
    elem = node.elem
    free(node)
    return elem, .None
}

reverse :: proc(l: ^List) {
    if l.size == 1 { return }

    iter := l.head

    for iter != nil {
        tmp := iter.next
        iter.prev, iter.next = iter.next, iter.prev
        iter = tmp
    }
    l.head, l.tail = l.tail, l.head
}

count :: proc(l: List) -> int {
    return l.size
}

delete :: proc(l: ^List, value: int) {
    if l.size == 1 && l.head.elem == value {
        free(l.head)
        l.head, l.tail, l.size = nil, nil, 0
        return
    }

    for iter := l.head; iter != nil; iter = iter.next {
        if iter.elem == value {
            if iter.next == nil {
                if iter.prev != nil {
                    iter.prev.next = nil
                }
                l.tail = iter.prev
            } else if iter.prev == nil {
                if iter.next != nil {
                    iter.next.prev = nil
                }
                l.head = iter.next
            } else {
                iter.next.prev, iter.prev.next = iter.prev, iter.next
            }
            free(iter)
            l.size -= 1
            return
        }
    }
}
