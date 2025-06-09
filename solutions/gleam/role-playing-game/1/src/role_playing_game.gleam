import gleam/option.{type Option, None, Some}

pub type Player {
  Player(name: Option(String), level: Int, health: Int, mana: Option(Int))
}

pub fn introduce(player: Player) -> String {
  case player.name {
    Some(name) -> name
    None -> "Mighty Magician"
  }
}

pub fn revive(player: Player) -> Option(Player) {
  case player.health <= 0 {
    True -> case player.level >= 10 {
      True -> Some(Player(..player, health: 100, mana: Some(100)))
      False -> Some(Player(..player, health: 100))
    }
    False -> None
  }
}

pub fn cast_spell(player: Player, cost: Int) -> #(Player, Int) {
  case player.mana {
    None -> {
      case cost >= player.health {
        True -> #(Player(..player, health: 0), 0)
        False -> #(Player(..player, health: player.health - cost), 0)
      }
    } 
    Some(mana) -> {
      case mana < cost {
        True -> #(player, 0)
        False -> #(Player(..player, mana: Some(mana - cost)), 2 * cost)
      }
    }
  }
}
