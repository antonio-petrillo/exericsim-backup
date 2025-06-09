(module
  (func (export "eggCount") (param $number i32) (result i32)
    (local $count i32)
    (local.set $count (i32.const 0))

    (loop $popcount
      (if (i32.ne (i32.const 0) (local.get $number))
        (then
          (local.set $count (i32.add (i32.const 1) (local.get $count)))
          (local.set $number 
            (i32.and (local.get $number) (i32.sub (local.get $number) (i32.const 1))))
          (br $popcount))))
    
    (return (local.get $count))))
