(module
  (memory (export "mem") 1)

  (func (export "countNucleotides") (param $offset i32) (param $length i32) (result i32 i32 i32 i32)

    (local $i i32)
    (local $current i32)
    
    (local $A i32)
    (local $C i32)
    (local $G i32)
    (local $T i32)

    (local.set $current (i32.const 1000))
    (local.set $i (i32.const 0))
    (local.set $A (i32.const 0))
    (local.set $C (i32.const 0))
    (local.set $G (i32.const 0))
    (local.set $T (i32.const 0))
    (local.set $length (i32.sub (local.get $length) (i32.const 1)))
     (loop $count
        (if (i32.le_s (local.get $i) (local.get $length))
          (then 
          (local.set $current (i32.load8_u (i32.add (local.get $i) (local.get $offset))))
          ;; break if not a,c,g,t
          (if (i32.and (i32.and (i32.ne (i32.const 65) (local.get $current))
                                (i32.ne (i32.const 67) (local.get $current)))
                       (i32.and (i32.ne (i32.const 71) (local.get $current))
                                (i32.ne (i32.const 84) (local.get $current))))
            (then 
               (return 
                 (i32.const -1)
                 (i32.const -1)
                 (i32.const -1)
                 (i32.const -1))))
           (if (i32.eq (i32.const 65) (local.get $current)) 
             (then (local.set $A (i32.add (i32.const 1) (local.get $A)))))
            (if (i32.eq (i32.const 67) (local.get $current)) 
             (then (local.set $C (i32.add (i32.const 1) (local.get $C)))))
            (if (i32.eq (i32.const 71) (local.get $current)) 
             (then (local.set $G (i32.add (i32.const 1) (local.get $G)))))
            (if (i32.eq (i32.const 84) (local.get $current)) 
             (then (local.set $T (i32.add (i32.const 1) (local.get $T)))))
            (local.set $i (i32.add (local.get $i) (i32.const 1)))
            (br $count))))
    
    (return 
      (local.get $A)
      (local.get $C)
      (local.get $G)
      (local.get $T))))
