local DNA = {}
count = {A = 0, C = 0, T = 0, G = 0}

function DNA:new(input)
  self.count = {A = 0, C = 0, T = 0, G = 0}
  for char in input:gmatch(".") do
    local tmp = count[char]
    if tmp == nil then
      error("Invalid Sequence")
    end
    count[char] = tmp + 1
  end
  return count
end

return DNA
