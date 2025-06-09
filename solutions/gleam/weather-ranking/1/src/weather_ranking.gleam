import gleam/float
import gleam/list
import gleam/order.{type Order, Eq}
import gleam/string

pub type City {
  City(name: String, temperature: Temperature)
}

pub type Temperature {
  Celsius(Float)
  Fahrenheit(Float)
}

pub fn fahrenheit_to_celsius(f: Float) -> Float {
  { f -. 32. } /. 1.8
}

pub fn compare_temperature(left: Temperature, right: Temperature) -> Order {
  case left, right {
    Celsius(t1), Celsius(t2) -> float.compare(t1, t2)
    Fahrenheit(t1), Fahrenheit(t2) -> float.compare(t1, t2)
    Celsius(t1), Fahrenheit(t2) -> float.compare(t1, fahrenheit_to_celsius(t2))
    Fahrenheit(t1), Celsius(t2) -> float.compare(fahrenheit_to_celsius(t1), t2)
  }
}

pub fn sort_cities_by_temperature(cities: List(City)) -> List(City) {
  cities
  |> list.sort(fn (c1, c2) {
      case compare_temperature(c1.temperature, c2.temperature) {
        Eq -> string.compare(c1.name, c2.name)
        ord -> ord
      }
    })
}
