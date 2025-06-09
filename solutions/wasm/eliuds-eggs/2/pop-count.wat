(module
  (func (export "eggCount") (param $number i32) (result i32)
    (return (i32.popcnt (local.get $number)))))
