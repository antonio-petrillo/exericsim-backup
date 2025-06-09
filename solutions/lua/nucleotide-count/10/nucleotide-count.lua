local DNA = { nucleotideCounts = {A = 0, T = 0, C = 0, G = 0} }

function DNA:new(input)
  self.nucleotideCounts = {A = 0, T = 0, C = 0, G = 0}
  for char in input:gmatch(".") do
    local tmp = nucleotideCounts[char]
    if tmp == nil then
      error("Invalid Sequence")
    end
    nucleotideCounts[char] = tmp + 1
  end
end

return DNA
