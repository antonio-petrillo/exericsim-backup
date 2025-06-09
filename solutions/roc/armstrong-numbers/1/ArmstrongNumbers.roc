module [isArmstrongNumber]

isArmstrongNumber : U64 -> Bool
isArmstrongNumber = \number -> 
    digits = lenDigits number 0

    loop = \acc, n ->
                when n is 
                    0 -> acc
                    _ -> loop (acc + Num.powInt (n % 10) digits) (n // 10)
    
    loop 0 number == number
        

lenDigits : U64, U64 -> U64
lenDigits = \n, acc -> when n is 
        0 -> acc
        _ -> lenDigits (n // 10) (acc + 1)