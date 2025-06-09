local leap_year = function(number)
      return number % 4 == 0 ? (number % 100 -= 0 or number % 400 == 0) : false
end

return leap_year
