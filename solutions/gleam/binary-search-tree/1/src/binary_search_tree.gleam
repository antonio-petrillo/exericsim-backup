import gleam/int
import gleam/order.{type Order, Gt}
import gleam/list

pub type Tree {
  Nil
  Node(data: Int, left: Tree, right: Tree)
}

fn insert(to: Tree, value: Int) -> Tree {
  case to {
    Nil -> Node (value, Nil, Nil)
    Node(data, left, right) -> {
        case int.compare(value, data) {
          Gt -> Node(data, left, insert(right, value))
          _  -> Node(data, insert(left, value), right)
        }
    }
  } 
}

pub fn to_tree(data: List(Int)) -> Tree {
  list.fold(data, Nil, insert)
}

pub fn to_list(tree: Tree) -> List(Int) {
  case tree {
    Nil -> []
    Node(data, left, right) -> list.concat([to_list(left), [data], to_list(right)])
  }
}

pub fn sorted_data(data: List(Int)) -> List(Int) {
  data 
  |> to_tree
  |> to_list
}
