import gleam/iterator

pub type Triplet {
  Triplet(Int, Int, Int)
}

pub fn triplets_with_sum(sum: Int) -> List(Triplet) {
  iterator.range(1, sum / 3)
  |> iterator.flat_map(fn(el) {
    iterator.range(el + 1, sum / 2) 
    |> iterator.map(fn (el2) {
        Triplet(el, el2, sum - el - el2)
    })
  })
  |> iterator.filter(fn(t: Triplet) {
    let Triplet(a, b, c) = t

    a * a + b * b == c * c
  })
  |> iterator.to_list
}
