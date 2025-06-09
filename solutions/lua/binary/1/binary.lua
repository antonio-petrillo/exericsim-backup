local function to_decimal(input)
    local num = 0
    local pow = 1
    for digit in string.reverse(input):gmatch(".") do
        if digit == "1" or digit == "0" then
            if digit == "1" then
                num = num + pow
            end
        else
            return 0
        end
        pow = pow * 2
    end
    return num
end

return {
  to_decimal = to_decimal
}
