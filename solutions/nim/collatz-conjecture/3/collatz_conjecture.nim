proc steps*(n: int): int =
  if n < 1: raise newException(ValueError, "Input must be positive")
  var input: int = n
  while input != 1:
    result.inc
    if (input and 1) == 0:
      input = input shr 1
    else:
      input = 3 * input + 1