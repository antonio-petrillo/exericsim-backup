defmodule DNA do
  def encode_nucleotide(?A), do: 0b0001
  def encode_nucleotide(?C), do: 0b0010
  def encode_nucleotide(?G), do: 0b0100
  def encode_nucleotide(?T), do: 0b1000
  def encode_nucleotide(?\s), do: 0b0000

  def decode_nucleotide(0b0001), do: ?A
  def decode_nucleotide(0b0010), do: ?C
  def decode_nucleotide(0b0100), do: ?G
  def decode_nucleotide(0b1000), do: ?T
  def decode_nucleotide(0b0000), do: ?\s


  defp do_encode([], acc), do: acc
  defp do_encode([nucleotide | remaining], acc) do
    do_encode(remaining, <<acc::bitstring, encode_nucleotide(nucleotide)::4>>)
  end

  def encode(dna), do: do_encode(dna, <<>>)

  defp do_decode(<<>>, acc), do: Enum.reverse(acc)
  defp do_decode(<<nucleotide::4, remaining::bitstring>>, acc) do
    do_decode(remaining, [ decode_nucleotide(nucleotide) | acc ])
  end

  def decode(encoded_dna), do: do_decode(encoded_dna, [])
end
