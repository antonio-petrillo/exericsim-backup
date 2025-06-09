import gleam/list
import gleam/set

pub type Tree(a) {
  Nil
  Node(value: a, left: Tree(a), right: Tree(a))
}

pub type Error {
  DifferentLengths
  DifferentItems
  NonUniqueItems
}

pub fn tree_from_traversals(
  inorder inorder: List(a),
  preorder preorder: List(a),
) -> Result(Tree(a), Error) {
  let size1 = list.length(inorder)
  let size2 = list.length(preorder)

  let set1 = set.from_list(inorder)
  let set2 = set.from_list(preorder)

  let uniques = set.size(set1) != size1

  let not_same_elems = set1 != set2

  case size1 != size2, not_same_elems,  uniques {
    True, _, _ -> Error(DifferentLengths)
    _, True, _ -> Error(DifferentItems)
    _, _, True -> Error(NonUniqueItems)
    _, _, _ -> Ok(build_tree(inorder, preorder))
  }
}

fn split(inorder: List(a), root: a) -> #(List(a), List(a)) {
  let #(left, right) = list.split_while(inorder, fn (el: a) -> Bool { el != root })

  #(left, list.drop(right, 1))
}

fn build_tree(inorder: List(a), preorder: List(a)) -> Tree(a) {
  case preorder {
    [] -> Nil
    [leaf] -> Node(value: leaf, left: Nil, right: Nil)
    [root, ..rest] -> {
      let #(left, right) = split(inorder, root)
      let #(rest_left, rest_right) = list.split(rest, list.length(left))

      Node(
        value: root,
        left: build_tree(left, rest_left),
        right: build_tree(right, rest_right)
      )
    }
  }
}
