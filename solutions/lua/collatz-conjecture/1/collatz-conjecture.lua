return function(n)
    if n <= 0 then
        error("Only positive numbers are allowed")
    end
    local steps = 0
    while n ~= 1 do
        steps = steps + 1
        if n % 2 == 0 then
            n = n / 2
        else
            n = 3 * n + 1
        end
    end
    return steps
end
