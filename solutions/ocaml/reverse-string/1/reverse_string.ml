let reverse_string s =
  let len = String.length s in
  let rec reverse_tail size acc =
    if size = -1 then acc
    else reverse_tail (size - 1)  (acc ^ String.make 1 s.[size])
  in 
    if len = 0 then "" else reverse_tail (len - 1) ""
    