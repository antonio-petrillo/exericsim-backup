import gleam/result

pub type Player {
  Black
  White
}

pub type Game {
  Game(
    white_captured_stones: Int,
    black_captured_stones: Int,
    player: Player,
    error: String,
  )
}

fn other_state(game: Game) {
  case game.player {
    White -> Game(..game, player: Black)
    Black -> Game(..game, player: White)
  }
}

pub fn apply_rules(
  game: Game,
  rule1: fn(Game) -> Result(Game, String),
  rule2: fn(Game) -> Game,
  rule3: fn(Game) -> Result(Game, String),
  rule4: fn(Game) -> Result(Game, String),
) -> Game {
  case game
  |> rule2
  |> rule1
  |> result.try(rule3)
    |> result.try(rule4) {
    Ok(next) -> other_state(next)
    Error(err) -> Game(..game, error: err)
  }

}
