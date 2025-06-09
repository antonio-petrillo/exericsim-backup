import gleam/list

pub type Pizza {
  Margherita
  Caprese
  Formaggio
  ExtraSauce(Pizza)
  ExtraToppings(Pizza)
}

fn pizza_price_tail_rec(extra: Int, pizza: Pizza) -> Int {
  case pizza {
    Margherita -> 7 + extra
    Caprese -> 9 + extra
    Formaggio -> 10 + extra
    ExtraSauce(pizza) -> pizza_price_tail_rec(1 + extra, pizza)
    ExtraToppings(pizza) -> pizza_price_tail_rec(2 + extra, pizza)
  }
}

pub fn pizza_price(pizza: Pizza) -> Int {
  pizza_price_tail_rec(0, pizza)
}

pub fn order_price(order: List(Pizza)) -> Int {
  let total = list.fold(order, 0, pizza_price_tail_rec)
  case list.length(order) {
    1 -> total + 3
    2 -> total + 2
    _ -> total
  }
}
