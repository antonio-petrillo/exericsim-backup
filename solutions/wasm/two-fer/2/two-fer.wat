(module
  (memory (export "mem") 1)

	(data (i32.const 0) "One for ")
	(data (i32.const 8) "you")
	(data (i32.const 11) ", one for me.") 
  
   (func (export "twoFer") (param $offset i32) (param $length i32) (result i32 i32)
        
    (local $msg i32)
    (local.set $msg (i32.const 0))
     
     ;; no argument
    (if (i32.eq (local.get $length) (i32.const 0))
      (then (return (i32.const 0) (i32.const 24))))

     ;; with one argument
     (memory.copy (i32.add (i32.const 24) (local.get $msg)) (i32.const 0) (i32.const 8))
     (local.set $msg (i32.add (local.get $msg) (i32.const 8)))
     (memory.copy (i32.add (i32.const 24) (local.get $msg)) (local.get $offset) (local.get $length))
     (local.set $msg (i32.add (local.get $msg) (local.get $length)))
     (memory.copy (i32.add (i32.const 24) (local.get $msg)) (i32.const 11) (i32.const 23))
     (local.set $msg (i32.add (local.get $msg) (i32.const 13)))
     
     (return (i32.const 24) (local.get $msg))))

