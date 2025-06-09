proc primeFactors*(n: int64): seq[int] =
  var nn: int64 = n
  while nn mod 2.int64 == 0:
    result.add(2.int64)
    nn = nn div 2.int64
  var factor = 3.int64
  while nn != 1:
    if nn mod factor == 0.int64:
      result.add(factor)
      nn = nn div factor
    else:
      factor += 2.int64
