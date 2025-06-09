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

pub fn apply_rules(
  game: Game,
  rule1: fn(Game) -> Result(Game, String),
  rule2: fn(Game) -> Game,
  rule3: fn(Game) -> Result(Game, String),
  rule4: fn(Game) -> Result(Game, String),
) -> Game {
  let computed = game
             |> rule2
             |> rule1
             |> result.try(rule3)
             |> result.try(rule4)

  let next_player = case game.player {
    Black -> White
    White -> Black
  }

  case computed {
    Ok(next) -> Game(..next, player: next_player)
    Error(err) -> Game(..game, error: err)
  }

}
