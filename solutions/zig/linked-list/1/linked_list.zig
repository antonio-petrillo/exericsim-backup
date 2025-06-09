pub fn LinkedList(comptime T: type) type {
    return struct {
        const Self = @This();

        pub const Node = struct {
            prev: ?*Node = null,
            next: ?*Node = null,
            data: T,
        };

        first: ?*Node = null,
        last: ?*Node = null,
        len: usize = 0,

        pub fn push(self: *Self, node: *Node) void {
            if (self.last) |tail| {
                node.prev = tail;
                tail.next = node;
            } else {
                self.first = node;
            }
            self.last = node;
            self.len += 1;
        }

        pub fn pop(self: *Self) ?*Node {
            if (self.last) |tail| {
                self.last = tail.prev;
                tail.prev = null;
                if (self.last) |_tail| {
                    _tail.next = null;
                } else {
                    self.first = null;
                }
                self.len -= 1;
                return tail;
            } else {
                return null;
            }
        }

        pub fn shift(self: *Self) ?*Node {
            if (self.first) |head| {
                self.first = head.next;
                head.next = null;
                if (self.first) |_head| {
                    _head.prev = null;
                } else {
                    self.last = null;
                }
                self.len -= 1;
                return head;
            } else {
                return null;
            }
        }

        pub fn unshift(self: *Self, node: *Node) void {
            if (self.first) |head| {
                head.prev = node;
                node.next = head;
            } else {
                self.last = node;
            }
            self.first = node;
            self.len += 1;
        }

        pub fn delete(self: *Self, node: *Node) void {
            var iter = self.first;
            while (iter) |curr| : (iter = curr.next) {
                if (curr == node) {
                    break;
                }
            }
            var _node = iter orelse return;
            if (_node.next) |next| {
                if (_node.prev) |prev| {
                    next.prev = prev;
                    prev.next = next;
                    _node.next = null;
                    _node.prev = null;
                } else {
                    self.first = _node.next;
                    if (self.last == node) {
                        self.last = null;
                    }
                }
            } else {
                self.last = _node.prev;
                if (self.first == node) {
                    self.first = null;
                }
            }
            self.len -= 1;
        }
    };
}
