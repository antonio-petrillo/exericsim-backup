import gleam/float
import gleam/iterator
import gleam/int
import gleam/list
import gleam/order

pub type Character {
  Character(
    charisma: Int,
    constitution: Int,
    dexterity: Int,
    hitpoints: Int,
    intelligence: Int,
    strength: Int,
    wisdom: Int,
  )
}

pub fn generate_character() -> Character {
  let constitution = ability()

  Character(
    charisma: ability(),
    constitution: constitution,
    dexterity: ability(),
    hitpoints: 10 + modifier(constitution),
    intelligence: ability()  ,
    strength: ability(),
    wisdom: ability(),
  )
}

pub fn modifier(score: Int) -> Int {
  { int.to_float(score) -. 10. } /. 2. 
  |> float.floor
  |> float.truncate
}

pub fn ability() -> Int {
  fn() { 1 + int.random(6) }
  |> iterator.repeatedly
  |> iterator.take(4)
  |> iterator.to_list
  |> list.sort(order.reverse(int.compare))
  |> list.take(3)
  |> int.sum
}