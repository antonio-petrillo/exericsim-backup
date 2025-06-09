import gleam/dict
import gleam/set
import gleam/list
import gleam/string
import gleam/option.{type Option, None, Some}
import gleam/result

pub opaque type School {
  School(all: set.Set(String), grades: dict.Dict(Int, List(String)))
}

pub fn create() -> School {
  School(all: set.new(), grades: dict.new())
}

pub fn roster(school: School) -> List(String) {
  school.grades
  |> dict.values
  |> list.map(fn(l) { list.sort(l, string.compare) })
  |> list.concat
}

pub fn add(
  to school: School,
  student student: String,
  grade grade: Int,
) -> Result(School, Nil) {
  case set.contains(school.all, student) {
    True -> Error(Nil)
    _ -> School(
        all: set.insert(school.all, student),
        grades: dict.upsert(school.grades, grade, fn(g) {
          case g {
            Some(rest) -> [student, ..rest]
            _ -> [student]
          }
        })
      ) |> Ok
  }
}

pub fn grade(school: School, desired_grade: Int) -> List(String) {
  school.grades
  |> dict.get(desired_grade)
  |> result.unwrap([])
}
