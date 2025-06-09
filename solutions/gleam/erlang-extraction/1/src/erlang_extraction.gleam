pub type GbTree(k, v)

@external(erlang, "gb_trees", "empty")
pub fn new_gb_tree() -> GbTree(k, v)

pub fn insert(tree: GbTree(k, v), key: k, value: v) -> GbTree(k, v) {
  gb_insert(key, value, tree)
}

@external(erlang, "gb_trees", "insert")
fn gb_insert(key: k, value: v, tree: GbTree(k, v)) -> GbTree(k, v)


pub fn delete(tree: GbTree(k, v), key: k) -> GbTree(k, v) {
  gb_delete(key, tree)
}

@external(erlang, "gb_trees", "delete_any")
fn gb_delete(key: k, tree: GbTree(k, v)) -> GbTree(k, v)