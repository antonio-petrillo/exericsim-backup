import gleam/float

pub fn score(x: Float, y: Float) -> Int {
  let assert Ok(distance) = float.square_root(x *. x +. y *. y)

  case distance <=. 1.0, distance <=. 5.0, distance <=. 10.0 {
    True, _, _ -> 10
    False, True, _ -> 5
    False, False, True -> 1
    False, False, False -> 0
  }
}
