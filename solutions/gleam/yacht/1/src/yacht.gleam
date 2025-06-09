import gleam/list
import gleam/function
import gleam/dict.{type Dict}
import gleam/int

import exercism/test_runner.{ debug }


pub type Category {
  Ones
  Twos
  Threes
  Fours
  Fives
  Sixes
  FullHouse
  FourOfAKind
  LittleStraight
  BigStraight
  Choice
  Yacht
}

fn score_single(dice: List(Int), target: Int) -> Int {
  dice
  |> list.filter(fn(x) {x == target})
  |> list.length
  |> fn(count) {count * target}
}

fn score_list(given: List(Int), target: List(Int), points: Int) -> Int {  
  case given {
    pattern if pattern == target -> points
    _ -> 0
  }
}

fn get_four_of_a_kind_points(grouped_dict: Dict(Int, Int)) -> Int {
  grouped_dict 
  |> dict.filter(fn(_, v) {v == 4 || v == 5})
  |> dict.keys
  |> int.sum
  |> fn(n) {n * 4}
}

pub fn score(category: Category, dice: List(Int)) -> Int {
  let grouped_dict = dice 
                   |> list.group(function.identity)
                   |> dict.map_values(fn(_, l) { list.length(l) })
  
  let grouped = grouped_dict
                |> dict.values
                |> list.sort(int.compare)

  let sorted = list.sort(dice, int.compare)

  case category {
    Ones -> score_single(dice, 1)
    Twos -> score_single(dice, 2)
    Threes -> score_single(dice, 3)
    Fours -> score_single(dice, 4)
    Fives -> score_single(dice, 5)
    Sixes -> score_single(dice, 6)
    Choice -> int.sum(dice)
    FullHouse -> score_list(grouped, [2, 3], int.sum(dice))
    FourOfAKind -> {
        let points = get_four_of_a_kind_points(grouped_dict)
        case grouped {
          [1, 4] -> points
          [5] -> points
          _ -> 0
        }
    }
    LittleStraight -> score_list(sorted, [1, 2, 3, 4, 5], 30)
    BigStraight -> score_list(sorted, [2, 3, 4, 5, 6], 30)
    Choice -> int.sum(dice)
    Yacht -> score_list(grouped, [5], 50)
  }
}
