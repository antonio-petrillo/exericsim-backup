import gleam/list
import gleam/string

pub type Robot {
  Robot(direction: Direction, position: Position)
}

pub type Direction {
  North
  East
  South
  West
}

pub type Position {
  Position(x: Int, y: Int)
}

pub fn create(direction: Direction, position: Position) -> Robot {
  Robot(direction, position)
}

fn turn_left(direction: Direction) -> Direction {
  case direction {
    North -> West
    West -> South
    South -> East
    East -> North
  }
}

fn turn_right(direction: Direction) -> Direction {
  case direction {
    North -> East
    East -> South
    South -> West
    West -> North
  }
}

fn advance(position: Position, direction: Direction) -> Position {
  let x =  position.x
  let y =  position.y
  case direction {
    North -> Position(x, y + 1)
    South -> Position(x, y - 1)
    East -> Position(x + 1, y)
    West -> Position(x - 1, y)
  }
}

pub fn move(
  direction: Direction,
  position: Position,
  instructions: String,
) -> Robot {
  let instrs = string.split(instructions, "")
  let advance = fn(robot, instruction) {
    case instruction {
      "L" -> Robot(..robot, direction: turn_left(robot.direction))
      "R" -> Robot(..robot, direction: turn_right(robot.direction))
        _ -> Robot(..robot, position: advance(robot.position, robot.direction))
    }
  }

  list.fold(instrs, create(direction, position), advance)
}
