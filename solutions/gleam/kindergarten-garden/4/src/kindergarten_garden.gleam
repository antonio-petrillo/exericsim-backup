import gleam/iterator
import gleam/list
import gleam/string

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

fn diagram_to_plants(row: String) -> List(List(Plant)) {
  row
  |> string.to_graphemes
  |> list.map(string_to_plant)
  |> list.sized_chunk(2)
}  

pub fn plants(diagram: String, student: Student) -> List(Plant) {
  let pos = student_to_pos(student)

  diagram
  |> string.split("\n")
  |> list.map(diagram_to_plants)
  |> list.flat_map(fn(line_of_pair_of_plants) {
      let assert Ok(plants) = line_of_pair_of_plants
                            |> iterator.from_list
                            |> iterator.at(pos)
      plants
  })
}
