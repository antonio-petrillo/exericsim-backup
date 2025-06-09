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

fn diagram_to_plants(diagram: String) -> List(List(List(Plant))) {
  diagram
  |> string.split("\n")
  |> list.map(string.to_graphemes)
  |> list.map(fn(line) { list.map(line, string_to_plant) })
  |> list.map(fn(line_of_plants) { list.sized_chunk(line_of_plants, 2) } )
}  

pub fn plants(diagram: String, student: Student) -> List(Plant) {
  let pos = student_to_pos(student)

  diagram
  |> diagram_to_plants
  |> list.flat_map(fn(line_of_pair_of_plants) {
      let zipped = list.zip(
                      iterator.range(0, 11) |> iterator.to_list,
                      line_of_pair_of_plants
                    )

      let assert Ok(plants) = list.key_find(zipped, pos)
      plants
  })
}
