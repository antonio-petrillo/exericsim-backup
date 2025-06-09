(module
  (func (export "steps") (param $number i32) (result i32)
    (local $i i32)
    (local.set $i (i32.const 0))

    (i32.le_s (local.get $number) (i32.const 0))
    (if 
      (then (return (i32.const -1))))
    
    (loop $collatz
      (if (i32.ne (i32.const 1) (local.get $number))
        (then
          (local.set $i (i32.add (i32.const 1) (local.get $i)))
          (i32.rem_u (local.get $number) (i32.const 2))
          (if
            (then 
              (local.set $number (i32.add (i32.const 1) (i32.mul (i32.const 3) (local.get $number)))))
            (else 
              (local.set $number (i32.shr_u (local.get $number) (i32.const 1)))))
          (br $collatz))))
    (return (local.get $i))))