proc reverse*(s: string): string =
  var rev = ""
  for c in s:
    rev = c & rev
  return rev
