module EliudsEggs exposing (eggCount)

import Bitwise

eggCount : Int -> Int
eggCount n =
    let 
        eggRec num count =
            if num == 0 then
                count
            else
                eggRec (Bitwise.and num (num - 1)) (count + 1)
    in
        eggRec n 0
