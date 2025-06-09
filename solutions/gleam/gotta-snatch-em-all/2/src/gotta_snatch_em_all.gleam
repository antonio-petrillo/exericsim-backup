import gleam/set.{type Set}
import gleam/list
import gleam/string

pub fn new_collection(card: String) -> Set(String) {
  set.new()
  |> set.insert(card)
}

pub fn add_card(collection: Set(String), card: String) -> #(Bool, Set(String)) {
  #(set.contains(collection, card), set.insert(collection, card))
}

pub fn trade_card(
  my_card: String,
  their_card: String,
  collection: Set(String),
) -> #(Bool, Set(String)) {
  let new_set = collection 
                |> set.delete(my_card) 
                |> set.insert(their_card)

  let worth_trade = set.contains(collection, my_card) && !set.contains(collection, their_card)

  #(worth_trade, new_set)
}

pub fn boring_cards(collections: List(Set(String))) -> List(String) {
  case collections {
      [] -> []
      [head, ..rest] -> list.fold(rest, head, set.intersection) 
                        |> set.to_list 
                        |> list.sort(string.compare)
  }
}

pub fn total_cards(collections: List(Set(String))) -> Int {
  case collections {
     [] -> 0
     [head, ..rest] -> list.fold(rest, head, set.union) 
                        |> set.to_list     
                        |> list.length
  }
}

pub fn shiny_cards(collection: Set(String)) -> Set(String) {
  collection
  |> set.filter(fn(card) { string.starts_with(card, "Shiny ") })
}
