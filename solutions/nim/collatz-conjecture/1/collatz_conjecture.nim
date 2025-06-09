proc steps*(n: int): int =
  if n < 1: raise newException(ValueError, "Input must be positive")
  var input: int = n
  while input != 1:
    result += 1
    if input mod 2 == 0:
      input = input div 2
    else:
      input = 3 * input + 1