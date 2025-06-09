import std/tables

proc countDna*(s: string): CountTable[char] =
  var count = initCountTable[char]()

  for n in s:
    if n in "ACGT":
      count.inc(n)
    else:
      raise newException(ValueError, "Uknown nucleotide")

  count
