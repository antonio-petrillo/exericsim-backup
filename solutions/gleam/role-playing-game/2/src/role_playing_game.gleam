import gleam/option.{type Option, None, Some}
import gleam/int

pub type Player {
  Player(name: Option(String), level: Int, health: Int, mana: Option(Int))
}

pub fn introduce(player: Player) -> String {
  option.unwrap(player.name, "Mighty Magician")
}

pub fn revive(player: Player) -> Option(Player) {
  case player.health, player.level {
    0, level if level >= 10  -> Some(Player(..player, health: 100, mana: Some(100)))
    0, _ -> Some(Player(..player, health: 100))
    _, _ -> None
  }
}

pub fn cast_spell(player: Player, cost: Int) -> #(Player, Int) {
  case player.mana {
    None ->  #(Player(..player, health: int.max(0, player.health - cost)), 0) 
    Some(mana) if mana < cost -> #(player, 0)
    Some(mana) -> #(Player(..player, mana: Some(mana - cost)), 2 * cost)  
  }
}