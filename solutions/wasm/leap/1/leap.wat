(module
  ;; Returns 1 if leap year, 0 otherwise
  (func (export "isLeap") (param $year i32) (result i32)
    (local $result i32)
    (local.set $result (i32.const 0))

   (if (i32.and
     (i32.eq (i32.const 0) (i32.rem_u (local.get $year) (i32.const 4)))
     (i32.or
       (i32.ne (i32.const 0) (i32.rem_u (local.get $year) (i32.const 100)))
       (i32.eq (i32.const 0) (i32.rem_u (local.get $year) (i32.const 400)))))
     
  (then (local.set $result (i32.const 1))))
    (return (local.get $result))))
