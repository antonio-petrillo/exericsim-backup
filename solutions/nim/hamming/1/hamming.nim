proc distance*(a, b: string): int =
  if len(a) != len(b):
    raise newException(ValueError, "strands must be of equal length")
  for i, a in pairs(a):
    if a != b[i]:
      result += 1
  return
