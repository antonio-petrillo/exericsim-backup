import gleam/string
import gleam/list

pub type Student {
  Alice
  Bob
  Charlie
  David
  Eve
  Fred
  Ginny
  Harriet
  Ileana
  Joseph
  Kincaid
  Larry
}

pub type Plant {
  Radishes
  Clover
  Violets
  Grass
}

fn string_to_plant(s: String) -> Plant {
  case s {
    "G" -> Grass
    "C" -> Clover
    "R" -> Radishes
    "V" -> Violets
    _ -> panic
  }
}

fn student_to_pos(s: Student) -> Int {
  case s {
    Alice -> 0
    Bob -> 1
    Charlie -> 2  
    David -> 3
    Eve -> 4
    Fred -> 5
    Ginny -> 6
    Harriet -> 7
    Ileana -> 8
    Joseph -> 9
    Kincaid -> 10
    Larry -> 11
  }
}

pub fn plants(diagram: String, student: Student) -> List(Plant) {
  let pos = student_to_pos(student)

  diagram
  |> string.split("\n")
  |> list.map(string.to_graphemes)
  |> list.map(fn(l) { list.map(l, string_to_plant) })
  |> list.map(fn(l) { list.sized_chunk(l, 2) } )
  |> list.flat_map(fn(l) {
      list.index_fold(l, [], fn(acc: List(Plant), el: List(Plant), idx: Int) -> List(Plant){
          case idx == pos {
            True -> el
            False -> acc
          }
      })
  })
}
