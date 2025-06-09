proc onSquare*(n: int): uint64 =
  if n < 1 or n > 64:
    raise newException(ValueError, "n must be in range [1, 64]")
  result = 1.uint64 shl (n - 1)

proc total*: uint64 =
  result = not 0.uint64
