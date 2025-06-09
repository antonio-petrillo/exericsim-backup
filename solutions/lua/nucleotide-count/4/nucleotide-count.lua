local DNA = {}

function DNA:new(input)
  local count = {A = 0, C = 0, T = 0, G = 0}
  for char in input:gmatch(".") do
    local tmp = count[char]
    if tmp == nil then
      error("Invalid Nucleotide")
    end
    count[char] = tmp + 1
  end
  return count
end

DNA:new("INVALID")

return DNA
