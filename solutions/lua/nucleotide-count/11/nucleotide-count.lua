DNA = { }
    
function DNA:new(input)
  self.nucleotideCounts = {A = 0, T = 0, C = 0, G = 0}
  for char in input:gmatch(".") do
    local tmp = self.nucleotideCounts[char]
    if tmp == nil then
      error("Invalid Sequence")
    end
    self.nucleotideCounts[char] = tmp + 1
  end
  return self
end

function DNA:count(nucleotide) 
    return self.nucleotideCounts[nucleotide] or error("Invalid Nucleotide")
end

return DNA